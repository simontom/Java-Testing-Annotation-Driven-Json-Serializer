package simon.tests.serialization

import org.json.JSONException
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

import simon.jsonserializer.dataobjects.Derived
import simon.jsonserializer.dataobjects.DerivedDoubled
import simon.jsonserializer.parser.exceptions.JsonSerializationException

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationDerivedTest : JsonSerializationTestBase() {

    @Test
    fun a_serialize_DerivedClass() {
        val derived = Derived(randomInt, randomInt, "bar data", true)

        serializeAndPrint("Derived", derived)
    }

    @Test
    fun b_serialize_DerivedDoubledClass() {
        val derivedDoubled = DerivedDoubled(randomInt, randomInt, "bar data", true)

        serializeAndPrint("Derived Doubled", derivedDoubled)
    }

}
