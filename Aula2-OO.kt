fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 7) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            0 -> {

                val animal = obterInformacoesDoAnimal()

                if (animal != null) {
                    repositorioAnimal.adicionar(animal)
                }
            }

            1 -> {
                repositorioAnimal.listar()
            }
            2 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            3 -> {
                val nomeAnimal: String? = readlnOrNull()
                if (nomeAnimal != null) {
                    repositorioAnimal.remover(nomeAnimal)
                }
            }
            4 -> {
                println("Consulte o animal pela cor: ")
                val cor = readlnOrNull() ?: ""

                obterCor(cor.toLowerCase())?.let {
                    repositorioAnimal.listarPorCor(it)
                        .forEach{ println("Animmal: ${it.nome}, Cor: ${it.cor}") }
                }
            }
            5 -> {
                println("Consulte pela idade")
                val idade = readlnOrNull()?.toIntOrNull() ?: 0

                repositorioAnimal.listarPorIdade(idade).forEach { println("Animal:" + it.nome) }
            }
            6 -> {
                println("Consulte o animal pelo nome: ")
                val nomeABuscar = readlnOrNull() ?: ""

                val animal = repositorioAnimal.buscarPorNome(nomeABuscar)
                if (animal != null) {
                    println("Animal encontradoo!!, seu nome é ${animal.nome}")
                }else{
                    println("Animal nao encontrado :(")
                }
            }
        }


        }

    }

fun obterInformacoesDoAnimal(): Animal? {
    print("Nome do animal: ")
    val nome = readLine() ?: ""

    print("Quantos anos ele tem? ")
    val idade = readLine()?.toIntOrNull() ?: 0

    print("Qual é a cor dele? ")
    val cor = readLine() ?: ""
    val corConvertida = obterCor(cor) ?: Color.RED // Supondo que Cor.BRANCO seja um valor padrão

   val tipo = readLine()?.toLowerCase()
     if(tipo == "cachorro"){
        return Cachorro(nome,idade,corConvertida).criarAnimal(nome,idade, corConvertida);
    }else if(tipo == "gato") {
        return Gato(nome,idade, corConvertida).criarAnimal(nome,idade, corConvertida);
    }else if (tipo == "passaro"){
         return Passaro(nome,idade, corConvertida).criarAnimal(nome,idade, corConvertida);
    }else if(tipo == "humano"){
         return Humano(nome,idade,corConvertida).criarAnimal(nome,idade, corConvertida);
     }


    return null;
}

enum class Color(val descricao: String) {
    RED("vermelho"),
    GREEN("verde"),
    BLUE("azul")
}
fun obterCor(corString: String): Color? {
    return when (corString.toLowerCase()) {
        "vermelho" -> Color.RED
        "verde" -> Color.GREEN
        "azul" -> Color.BLUE

        else -> null // Retorna null se a cor não for encontrada
    }
}

abstract class Animal(var nome:String, var idade: Int, val cor: Color) {

    abstract fun emitirSom()
    open fun idadeEmAnosHumanos(): Int {
        return this.idade * 7
    }

    abstract fun criarAnimal(nome: String, idade: Int, cor: Color): Animal


}



class Humano(nome:String, idade: Int,cor:Color): Animal(nome, idade, cor){
    override fun emitirSom() {
        println("Olá, Mundoo")
    }

    override fun idadeEmAnosHumanos(): Int {
        return this.idade
    }

    override fun criarAnimal(nome: String, idade: Int, cor: Color): Animal {
        return Humano(nome, idade,cor)

    }

}

class Cachorro(nome:String, idade: Int,cor: Color) : Animal(nome, idade, cor) {
    override fun emitirSom() {
        println("Au au")
    }
    override fun criarAnimal(nome: String, idade: Int, cor: Color): Animal {
        return Cachorro(nome, idade,cor)
    }
}
class Gato(nome:String, idade: Int, cor: Color) : Animal(nome, idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
    override fun criarAnimal(nome: String, idade: Int, cor: Color): Animal {
        return Gato(nome, idade, cor)
    }

}

class Passaro(nome:String, idade: Int, cor: Color) : Animal(nome, idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
    override fun criarAnimal(nome: String, idade: Int, cor: Color): Animal {
        return Passaro(nome, idade, cor)
    }

}

fun menu() {
    println("0 - Cadastrar Animal")
    println("1 - Listar Animais")
    println("2 - Emitir som")
    println("3 - Excluir animal")
    println("4 - Listar animais por cor")
    println("5 - Listar animais por idade")
    println("6 - Buscar animal por nome")
    println("7 - Sair")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }

    fun listarPorCor(cor: Color): List<Animal> {
        val animais = animais.filter { it.cor == cor }
        return animais;
    }
    fun listarPorIdade(idade: Int): List<Animal> {
        val animaisDeIdadeX = animais.filter{it.idade == idade}
        return animaisDeIdadeX;
    }

    fun remover(nomeAnimal: String): Boolean {
        var animalEncontrado = animais.filter { a -> a.nome == nomeAnimal}
        return animais.remove(animalEncontrado[0])
    }
    fun buscarPorNome(nome:String): Animal? {
        val animaisPesquisado =  animais.filter { it.nome == nome }
        return if (animaisPesquisado.isNotEmpty()){
            animaisPesquisado[0]
        }else{
            null
        }

    }


}

