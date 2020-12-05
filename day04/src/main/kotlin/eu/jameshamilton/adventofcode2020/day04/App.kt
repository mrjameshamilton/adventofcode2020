package eu.jameshamilton.adventofcode2020.day04

import java.io.InputStream

fun main() {
    println(part1(parse(object {}.javaClass.getResourceAsStream("/input.txt"))))
    println(part2(parse(object {}.javaClass.getResourceAsStream("/input.txt"))))
}

fun part1(passports: Sequence<Passport>) : Int = passports.count(::isComplete)

fun part2(passports: Sequence<Passport>) : Int = passports.filter(::isComplete).count(::isValid)

/**
byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
cid (Country ID) - ignored, missing or not.
*/

private fun isComplete(passport: Passport) =
    passport.byr != null &&
            passport.iyr != null &&
            passport.eyr != null &&
            passport.hgt != null &&
            passport.hcl != null &&
            passport.ecl != null &&
            passport.pid != null // cid is ignored!

fun isValid(passport: Passport) =
    isValidBirthYear(passport.byr) &&
    isValidIssueYear(passport.iyr) &&
    isValidExpirationYear(passport.eyr) &&
    isValidHeight(passport.hgt) &&
    isValidHairColor(passport.hcl) &&
    isValidEyeColor(passport.ecl) &&
    isValidPassportId(passport.pid) &&
    isValidCountryId(passport.cid)

fun isValidBirthYear(year: Int?) = year != null && 1920 <= year && year <= 2002
fun isValidIssueYear(year: Int?) = year != null && 2010 <= year && year <= 2020
fun isValidExpirationYear(year: Int?) = year != null && 2020 <= year && year <= 2030
fun isValidHeight(height: String?) = height != null && """(\d+)(cm|in)""".toRegex().findAll(height).count {
    val (h, unit: String) = it.destructured
    with (h.toInt()) {
        when (unit) {
            "cm" -> this in 150..193
            "in" -> this in 59..76
            else -> false
        }
    }
} != 0
fun isValidHairColor(color:String?) = color != null && """^#[0-9a-f]{6}$""".toRegex().matches(color)
fun isValidEyeColor(color:String?) = color in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
fun isValidPassportId(id:String?) = id != null && """^\d{9}$""".toRegex().matches(id)
fun isValidCountryId(id:Int?) = true

fun parse(inputStream: InputStream): Sequence<Passport> = sequence {
    inputStream.bufferedReader().useLines { lines ->
        val sb = StringBuilder()
        lines.forEach { s ->
            sb.append(" $s")
            if (s.isBlank()) {
                yield(Passport.fromString(sb.toString()))
                sb.clear()
            }
        }
        yield(Passport.fromString(sb.toString()))
    }
}

data class Passport(
    val byr: Int? = null,
    val iyr: Int? = null,
    val eyr: Int? = null,
    val hgt: String? = null,
    val hcl: String? = null,
    val ecl: String? = null,
    val pid: String? = null,
    val cid: Int? = null
) {

    companion object {
        fun fromString(s: String): Passport {
            val map = s.split( " ", "\n")
                .map { it.split(":") }
                .map { it.first() to it.last() }
                .toMap()
            return Passport(
                map["byr"]?.toInt(),
                map["iyr"]?.toInt(),
                map["eyr"]?.toInt(),
                map["hgt"],
                map["hcl"],
                map["ecl"],
                map["pid"],
                map["cid"]?.toInt()
            )
        }
    }
}
