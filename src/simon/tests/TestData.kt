package simon.tests

import simon.jsonserializer.dataobjects.*

object TestData {
    //region Instance Data
    const val int1 = 1
    const val int2 = 2
    const val int3 = 3
    const val int4 = 4

    const val str1 = "str1"
    const val str2 = "str2"
    const val str3 = "str3"

    const val bool1 = true
    const val bool2 = false

    val base_ok1 = Base(int1, int2)
    val base_ok2 = Base(int3, int4)
    val base_ex1 = Base(null, int2)

    val charstring_ok1 = TestCharString("string", 'c', 'o')

    val inner_ok1 = TestInner(int1.toLong(), str1)
    val withInner_ok1 = TestWithInner(null, str2, str3, inner_ok1)

    val derived_ok1 = Derived(int1, int2, str1, bool1)
    val derivedDoubled_ok1 = DerivedDoubled(int1, int2, str1, bool1)

    val intsArray_ok1 = arrayOf(int1, int2, int3)
    val intsList_ok1 = arrayListOf(int1, int2, int3)
    val intsMap_ok1 = hashMapOf(str1 to int1, str2 to int2, str3 to int3)

    val basesArray_ok1 = arrayOf(base_ok1, base_ok2)
    val basesList_ok1 = arrayListOf(base_ok1, base_ok2)
    val basesMap_ok1 = hashMapOf(str1 to base_ok1, str2 to base_ok2)

    val testArray_ok1 = TestArray(intsArray_ok1, basesArray_ok1)
    val testList_ok1 = TestList(intsList_ok1, basesList_ok1)
    val testMap_ok1 = TestMap(intsMap_ok1, TestData.basesMap_ok1)
    val testMap_ex1 = TestMap(intsMap_ok1, null)
    //endregion Instance Data

    //region Serialized to Json
    const val base_ok1_json = """
    {
        "_mandatory_and_converted_": "$int1",
	    "test_number": $int2
    }"""

    const val charstring_ok1_json = """
    {
        "char_object": "o",
        "char_primitive": "c",
        "mandatory": "string"
    }"""

    const val base_ex1_json = """
    {
	    "test_number": $int2
    }"""

    const val withInner_ok1_json = """
    {
        "bar": "$str2",
        "__converted__": "123",
        "floatAsObject": 12345.123,
        "doubleAsObject": 123456.123456,
        "longAsObject": 1234,
        "it_is_not_null": {
            "inner_foo": 11,
            "bool": true,
            "inner_bar": "22",
            "boolObject": true
        },
        "test_name": "$str3",
        "inner": {
            "inner_foo": $int1,
            "bool": true,
            "inner_bar": "$str1",
            "boolObject": true
        },
        "test_number": 42
    }"""

    const val derived_ok1_json = """
    {
        "derivedBool": $bool1,
        "derived_bar": "$str1",
        "_mandatory_and_converted_": "$int1",
        "test_number": $int2
    }"""

    const val derivedDoubled_ok1_json = """
    {
        "derivedBool": $bool1,
        "derived_doubled_xx": "$str1",
        "derived_bar": "$str1",
        "dd_yy": $bool1,
        "_mandatory_and_converted_": "$int1",
        "test_number": $int2
    }"""

    const val testArray_ok1_json = """
    {
        "arrayBases": [
            {
                "_mandatory_and_converted_": "$int1",
                "test_number": $int2
            },
            {
                "_mandatory_and_converted_": "$int3",
                "test_number": $int4
            }
        ],
        "arrayIntegers": [$int1, $int2, $int3]
    }"""

    const val testList_ok1_json = """
    {
        "listIntegers": [$int1, $int2, $int3],
        "listBases": [
            {
                "_mandatory_and_converted_": "$int1",
                "test_number": $int2
            },
            {
                "_mandatory_and_converted_": "$int3",
                "test_number": $int4
            }
        ]
    }"""

    const val testMap_ok1_json = """
    {
        "mapIntegers": {
            "$str3": $int3,
            "$str1": $int1,
            "$str2": $int2
        },
        "mapBases": {
            "$str1": {
                "_mandatory_and_converted_": "$int1",
                "test_number": $int2
            },
            "$str2": {
                "_mandatory_and_converted_": "$int3",
                "test_number": $int4
            }
        }
    }"""

    const val testMap_ex1_json = """
    {
        "mapIntegers": {
            "$str3": $int3,
            "$str1": $int1,
            "$str2": $int2
        }
    }"""
    //endregion Serialized to Json
}
