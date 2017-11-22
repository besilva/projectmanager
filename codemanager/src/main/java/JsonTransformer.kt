import com.google.gson.Gson

class JsonTransformer(): spark.ResponseTransformer{
    val gson = Gson()
    override fun render(p0: Any?): String {
        return gson.toJson(p0)
    }
}