import java.util.Scanner

abstract class Doc (val name:String)
class Note(private val noteName:String, val noteDesc:String):Doc(noteName)
class Archive(private val arcName:String):Doc(arcName)
{
    val notesList= mutableListOf<Note>()
}
class Menu<T:Doc>
{
    var listOfDocs:MutableList<T>? = null
    fun putList(list: MutableList<T>)
    {
        listOfDocs = list
    }
    fun createObj(c:Char)
    {

    }
    fun getMenu(c:Char )
    {

            when (c)
            {
                'a'-> {
                    println("Список архивов:")
                    println("0. Создать архив")

                }
                'n'->
                {
                    println("Список заметок:")
                    println("0. Создать заметку")
                }

        }
        var i = 1
        if (listOfDocs!=null)
        {
            for (d in listOfDocs!!)
            {
                println("$i. ${d.name}")
                i++
            }
        }
        println("$i. Выход")
        while(true)
        {
            print("> ")
            val input  = Scanner(System.`in`).nextLine().toString()
            try
            {
                val inputInt = input.toInt()
                val lastInd = listOfDocs?.size?.toInt()
                when(i)
                {

                }
            }
            catch (ex:Exception)
            {
                println("Данные введены некорректно")
            }

        }



    }
}
inline fun<reified T> checkType(list: MutableList<T>, type:T):Boolean
{
    return (list is T)
}
fun main(args: Array<String>) {
    println("Hello World!")

}