import org.bson.codecs.pojo.annotations.BsonId
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import java.time.LocalDateTime

@Serializable
data class Baralho(
    @BsonId val id: ObjectId = ObjectId(),
    val cartas: List<Carta>,
    val id_usuario: String
)

@Serializable
data class Carta(
    val topico: String,
    val tipo: String, // Ex: "multipleChoice"
    val pergunta: String,
    val resposta: Int, // índice da alternativa correta
    val alternativas: List<String>,
    val localizacao: Localizacao,
    val proxima_revisao: String
)

@Serializable
data class Localizacao(
    val lat: Double,
    val lon: Double
)