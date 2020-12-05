package eu.jameshamilton.adventofcode2020.day04


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AppTest {
    private val input = object {}.javaClass.getResourceAsStream("/input.txt")
    private val valid = object {}.javaClass.getResourceAsStream("/valid.txt")
    private val invalid = object {}.javaClass.getResourceAsStream("/invalid.txt")

    @Test fun testPart1() {
        assertEquals(2, part1(parse(input)))
    }

    @Test fun testPart2Valid() {
        parse(valid).forEach { assertTrue(isValid(it), it.toString()) }
    }

    @Test fun testPart2Invalid() {
        parse(invalid).forEach { assertFalse(isValid(it), it.toString()) }
    }

    @Test fun testPart2() {
        assertEquals(4, part2(parse(valid) + parse(invalid)))
    }

    @Test fun testValidBirthYear() {
        assertTrue(isValidBirthYear(1926))
        assertTrue(isValidBirthYear(1950))
        assertTrue(isValidBirthYear(1980))
    }

    @Test fun testInvalidBirthYear() {
        assertFalse(isValidBirthYear(1900))
        assertFalse(isValidBirthYear(2020))
    }

    @Test fun testValidIssueYear() {
        assertTrue(isValidIssueYear(2018))
        assertTrue(isValidIssueYear(2015))
        assertTrue(isValidIssueYear(2012))
    }

    @Test fun testInvalidIssueYear() {
        assertFalse(isValidIssueYear(2000))
        assertFalse(isValidIssueYear(2030))
    }

    @Test fun testValidExpirationYear() {
        assertTrue(isValidExpirationYear(2020))
        assertTrue(isValidExpirationYear(2025))
        assertTrue(isValidExpirationYear(2030))
    }

    @Test fun testInvalidExpirationYear() {
        assertFalse(isValidExpirationYear(2000))
        assertFalse(isValidExpirationYear(2035))
    }

    @Test fun testValidCmHeight() {
        assertTrue(isValidHeight("160cm"))
        assertTrue(isValidHeight("180cm"))
    }

    @Test fun testInvalidCmHeight() {
        assertFalse(isValidHeight("100cm"))
        assertFalse(isValidHeight("200cm"))
    }

    @Test fun testValidInHeight() {
        assertTrue(isValidHeight("59in"))
        assertTrue(isValidHeight("70in"))
        assertTrue(isValidHeight("74in"))
    }

    @Test fun testInvalidInHeight() {
        assertFalse(isValidHeight("100in"))
        assertFalse(isValidHeight("200in"))
    }

    @Test fun testInvalidHeight() {
        assertFalse(isValidHeight("1m"))
    }

    @Test fun testValidHairColor() {
        assertTrue(isValidHairColor("#12efab"))
        assertTrue(isValidHairColor("#123456"))
        assertTrue(isValidHairColor("#abcdef"))
    }

    @Test fun testInvalidHairColor() {
        assertFalse(isValidHairColor("#hello12"))
        assertFalse(isValidHairColor("123456"))
        assertFalse(isValidHairColor("#12345z"))
    }

    @Test fun testValidEyeColor() {
        listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").forEach { assertTrue(isValidEyeColor(it), it) }
    }

    @Test fun testInvalidEyeColor() {
        assertFalse(isValidEyeColor("blue"))
    }

    @Test fun testValidPassportId() {
        assertTrue(isValidPassportId("123456789"))
        assertTrue(isValidPassportId("000012345"))
    }

    @Test fun testInvalidPassportId() {
        assertFalse(isValidPassportId("1234"))
    }

    @Test fun testParse() {
        assertEquals(listOf(
            Passport(ecl = "gry", pid = "860033327", eyr = 2020, hcl = "#fffffd", byr = 1937, iyr = 2017, cid = 147, hgt = "183cm"),
            Passport(ecl = "amb", pid = "028048884", eyr = 2023, hcl = "#cfa07d", byr = 1929, iyr = 2013, cid = 350),
            Passport(ecl = "brn", pid = "760753108", eyr = 2024, hcl = "#ae17e1", byr = 1931, iyr = 2013, hgt = "179cm"),
            Passport(ecl = "brn", pid = "166559648", eyr = 2025, hcl = "#cfa07d", iyr = 2011, hgt = "59in")
        ), parse(input).toList())
    }
}
