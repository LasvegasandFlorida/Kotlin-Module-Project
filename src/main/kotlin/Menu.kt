import java.lang.Exception
import java.util.Scanner

class Menu<T:Doc>(private val flag: Char)
{
    private var listOfDocs = mutableListOf<T>()
    private val listOfFuncs = mutableListOf("Создать",
        "Список",
        "Выход")
    private fun showMenu()
    {
        var i = 1
        var docName = ""
        if (flag == 'a')
        {
            println("Список архивов:")
            docName = " архив"
        }

        if (flag == 'n')
        {
            println("Список заметок:")
            docName = " заметку"
        }

        for (f in listOfFuncs)
        {
            when (f) {
                "Создать" -> {
                    println("0. $f $docName")
                }
                "Список" -> {
                    when (listOfDocs.size) {
                    0-> i =1
                    else ->  i+=listOfDocs.size
                }
                    this.showStuff(listOfDocs)
                }
                "Выход" -> println("$i. $f")

            }
        }
    }
    fun userInput(note:T?)
    {
        var f= 0
        while (f!=-1)
        {
            if (note!=null)
            {
                println("Заметка: ${note.name}")
                println("Описание заметки: ${note.desc}")
                println("0. Редактировать заметку")
                println("1. Выход")
            }
            else showMenu()
            print("Введите пункт меню-> ")

                val string =Scanner(System.`in`).nextLine()
            try
            {
                val inputInt = string.toInt()
                if (note!=null) {
                    if (inputInt in 0..1) {
                        when (inputInt) {
                            0 -> {
                                print("Введите описание заметки -> ")
                                note.desc = Scanner(System.`in`).nextLine()
                            }

                            1 -> f = -1
                            else -> println("Ошибка: Цифра не входит в диапазон")
                        }

                    }

                }
                else
                    when(inputInt)
                    {
                        0 -> {listOfDocs = createNew(listOfDocs) as MutableList<T>}
                        in 1..listOfDocs.size-> {
                            if (listOfDocs[inputInt-1] is Archive)
                            {
                                val notesMenu:Menu<Note> = Menu('n')
                                notesMenu.listOfDocs = listOfDocs[inputInt-1].list
                                notesMenu.userInput(null)
                                listOfDocs[inputInt-1].list = notesMenu.listOfDocs
                            }
                            else
                            {
                                userInput(listOfDocs[inputInt-1])
                            }
                        }
                        listOfDocs.size+1 -> f = -1
                        else -> println("Ошибка: Цифра не входит в диапазон")
                    }
            }
            catch (ex:Exception)
            {
                println("Ошибка: Введите цифру!")
            }

        }
    }
    private fun showStuff (listOfStuff:MutableList<out Doc>)
    {
        for (a in listOfStuff)
            println("${listOfStuff.indexOf(a)+1}. ${a.name}")

    }
    private fun createNew(listOfStuff:MutableList<out Doc>): MutableList<in Doc>
    {
        val result:MutableList<in Doc> = mutableListOf()
        if (flag =='a')
        {
            print("Введите название архива -> ")
            val arch =Archive(Scanner(System.`in`).nextLine(), mutableListOf())
            for (a in listOfStuff)
                result.add(a)
            result.add(arch)
            return result
        }
        else
        {
            print("Введите название заметки -> ")
            val noteName = Scanner(System.`in`).nextLine()
            print("Введите описание заметки -> ")
            val noteDesc = Scanner(System.`in`).nextLine()
            val note =Note(noteName,noteDesc)
            for (n in listOfStuff)
                result.add(n)
            result.add(note)
            return result
        }

    }

}