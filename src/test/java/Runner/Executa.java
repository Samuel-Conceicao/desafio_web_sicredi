package Runner;


import Testes.Desafio;
import Testes.Desafio2;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(value={Desafio.class, Desafio2.class})

public class Executa {
}
