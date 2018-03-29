package simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import simon.tests.JsonParserTestBase
import simon.tests.TestData

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationDerivedTest : JsonParserTestBase() {

    @Test
    fun a_serialize_DerivedClass() {
//        serializeAndPrint("Derived", TestData.derived_ok1)
        val json = jsonParser.serialize(TestData.derived_ok1)
    }

    @Test
    fun b_serialize_DerivedDoubledClass() {
//        serializeAndPrint("Derived Doubled", TestData.derivedDoubled_ok1)
        val json = jsonParser.serialize(TestData.derivedDoubled_ok1)
    }

}
