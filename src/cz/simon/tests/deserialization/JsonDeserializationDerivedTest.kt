package cz.simon.tests.deserialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import cz.simon.tests.JsonParserTestBase
import cz.simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonDeserializationDerivedTest : JsonParserTestBase() {

    @Test
    fun a_deserialize_DerivedClass() {
        deserializeAndCompare(TestData.derived_ok1, TestData.derived_ok1_json)
    }

    @Test
    fun b_deserialize_DerivedDoubledClass() {
        deserializeAndCompare(TestData.derivedDoubled_ok1, TestData.derivedDoubled_ok1_json)
    }

}
