package cz.simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import cz.simon.tests.JsonParserTestBase
import cz.simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationDerivedTest : JsonParserTestBase() {

    @Test
    fun a_serialize_DerivedClass() {
        serializeAndCompare(TestData.derived_ok1_json, TestData.derived_ok1)
    }

    @Test
    fun b_serialize_DerivedDoubledClass() {
        serializeAndCompare(TestData.derivedDoubled_ok1_json, TestData.derivedDoubled_ok1)
    }

}
