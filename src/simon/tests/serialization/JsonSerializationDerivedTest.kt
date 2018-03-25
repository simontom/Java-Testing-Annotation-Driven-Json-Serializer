package simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

import simon.jsonserializer.dataobjects.Derived
import simon.jsonserializer.dataobjects.DerivedDoubled
import simon.tests.JsonParserTestBase

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationDerivedTest : JsonParserTestBase() {

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
