import java.lang.Exception
import java.util.Scanner
import kotlin.system.exitProcess

abstract class Doc (val name:String)
class Note(private val noteName:String, val noteDesc:String):Doc(noteName)
class Archive(private val arcName:String):Doc(arcName)
{
    val notesList= mutableListOf<Note>()
}
class Menu<T:Doc>()
{
    fun showMenu(listOfFuncs :MutableMap<String, (Menu<T>?) -> Unit>,listOfDocs: MutableList<T>)
    {
        var i = 0
        for (f in listOfFuncs)
        {
            if(f.key!="Список архивов")
            {
                println("$i. ${f.key}")
                i+=listOfDocs.size
            }
            else
                f.value(this)
        }
    }
    /*fun userInput(input:String):Int
    {
        try
        {
            val inputInt = input.toInt()
            if(inputInt in 0..list.size+1)
            return inputInt
            else
            {
                println("Цифра не входит в диапазон")
                return -2
            }
        }
        catch (ex:Exception)
        {
            println("Введите цифру")
            return -2
        }
    }*/
    fun showStuff (listOfStuff:MutableList<out Doc>)
    {
        for (a in listOfStuff)
            println("${listOfStuff.indexOf(a)+1}. ${a.name}")

    }
    fun createNew(listOfStuff:MutableList<out Doc>):MutableList<in Doc>
    {
        var result:MutableList<in Doc> = mutableListOf()
        print("Введите название архива -> ")
        val arch =Archive(Scanner(System.`in`).nextLine())
        for (a in listOfStuff)
            result.add(a)
        result.add(arch)
        return result
    }

}
inline fun<reified T> checkType(list: MutableList<T>, type:T):Boolean
{
    return (list is T)
}
fun main(args: Array<String>) {

    val archM:Menu<Archive> = Menu()
    val listOfArchives = mutableListOf<Archive>()
    val mapOfFuncs = mutableMapOf<String, (Menu<Archive>?)->Unit>(
        "Создать архив" to {
           menu-> menu?.createNew(listOfArchives)
        },
        "Список архивов" to {
                menu -> menu?.showStuff(listOfArchives)
        },
        "Выход" to {}
    )
    var noteM:Menu<Note> = Menu()
    var f = 0
    while (f!=-1)
    {
        archM.showMenu(mapOfFuncs,listOfArchives)
    }


}