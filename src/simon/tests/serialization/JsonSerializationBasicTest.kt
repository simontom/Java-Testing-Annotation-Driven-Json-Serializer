package simon.tests.serialization

import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

import simon.jsonserializer.dataobjects.Base
import simon.jsonserializer.dataobjects.TestClass
import simon.jsonserializer.dataobjects.TestInner
import simon.jsonserializer.parser.exceptions.JsonParserException
import simon.tests.JsonParserTestBase

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class JsonSerializationBasicTest : JsonParserTestBase() {

    @Test
    fun a_serialize_BaseClass() {
        val base = Base(randomInt, randomInt)

        serializeAndPrint("Base", base)
    }

    @Test(expected = JsonParserException::class)
    fun b_serialize_BaseClassThrowsException() {
        val base = Base(null, randomInt)

        serializeAndPrint("Base - Prints no data\nJsonParserException caught", base)
    }

    @Test
    fun c_serialize_WithInnerClass() {
        val inner = TestInner(randomInt.toLong(), "inner bar data")
        val withInner = TestClass("foo data", "bar data", "baz data", inner)

        serializeAndPrint("With Inner", withInner)
    }

}
