package simon.tests

import simon.jsonserializer.dataobjects.*

object TestData {
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

    val inner_ok1 = TestInner(int1.toLong(), str1)
    val withInner_ok1 = TestClass(str1, str2, str3, inner_ok1)

    val derived_ok1 = Derived(int1, int2, str1, bool1)
    val derivedDoubled_ok1 = DerivedDoubled(int1, int2, str1, bool1)

    val intsArray_ok1 = arrayOf(int1, int2, int3)
    val intsList_ok1 = listOf(int1, int2, int3)
    val intsMap_ok1 = hashMapOf(str1 to int1, str2 to int2, str3 to int3)

    val basesArray_ok1 = arrayOf(base_ok1, base_ok2)
    val basesList_ok1 = listOf(base_ok1, base_ok2)
    val basesMap_ok1 = hashMapOf(str1 to base_ok1, str2 to base_ok2)

    val testArray_ok1 = TestArray(intsArray_ok1, basesArray_ok1)
    val testList_ok1 = TestList(intsList_ok1, basesList_ok1)
    val testMap_ok1 = TestMap(intsMap_ok1, TestData.basesMap_ok1)
    val testMap_ex1 = TestMap(intsMap_ok1, null)
}