// di/KoinModule.kt
import com.example.flashlearn.controllers.BaralhoController
import com.ppm.database.MongoClientProvider
import com.ppm.repository.BaralhoRepository
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineDatabase

val appModule = module {
    single { MongoClientProvider() }
    single<CoroutineDatabase> { get<MongoClientProvider>().database }
    single { BaralhoRepository(get()) }
    single { BaralhoController(get()) }
}