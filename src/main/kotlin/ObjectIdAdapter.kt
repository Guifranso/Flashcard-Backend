package com.ppm

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import org.bson.types.ObjectId
import java.lang.reflect.Type

class ObjectIdAdapter : JsonSerializer<ObjectId>, JsonDeserializer<ObjectId> {
    override fun serialize(
        src: ObjectId,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement = JsonPrimitive(src.toHexString())      // "6816f0015833925e55efac68"

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ObjectId = ObjectId(json.asString)
}