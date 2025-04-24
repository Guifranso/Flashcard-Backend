// di/KoinModule.kt
import com.example.flashlearn.controllers.BaralhoController
import com.ppm.database.MongoClientProvider
import com.ppm.repository.BaralhoRepository
import org.koin.dsl.module

val appModule = module {
    single { MongoClientProvider() }
    single { get<MongoClientProvider>() }
    single { BaralhoRepository(get()) }
    single { BaralhoController(get()) }
}