package com.example.myapplication
import kotlinx.serialization.Serializable
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


@Serializable
data class Person(
    val lastName: String,
    val firstName: String,
    val patronymic: String,
    val birthDay: String
)




object PeopleStorage {
    val people = mutableListOf<Person>()
}




class Storage {

    // Вызывается из @Composable (например, из экрана формы) для сохранения
    @Composable
    fun SavePeopleComposable() {
        val context = LocalContext.current
        savePeopleToFile(context)
    }

    // Обычная функция без @Composable – реальная запись в файл
    fun savePeopleToFile(context: Context) {
        // Имя файла, например people.json
        val fileName = "people.json"
        val file = File(context.filesDir, fileName)  // filesDir – внутренняя папка приложения[web:1]

        // Преобразуем список людей в JSON-строку
        val jsonString = Json.encodeToString(PeopleStorage.people)  // список Person -> JSON[web:2][web:5][web:8]

        // Записываем JSON в файл (перезапись)
        file.writeText(jsonString)
    }

    // Пример: добавить человека в список
    fun addPerson(person: Person) {
        PeopleStorage.people.add(person)
    }
}
