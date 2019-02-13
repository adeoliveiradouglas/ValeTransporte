/* 
 * Classe usada para compara��o de nomes entre dois objetos atrav�s do Collections.sort, n�o importando qual a classe dos 
 * objetos envolvidos 
 * Ambos os objetos devem ter o m�todo "String getNome(){}" para que a compara��o seja feita corretamente
 */

package utilidades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

public class ComparadorNome implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
		String s1 = "", s2 = "";
		
		try {
			//cria um m�todo getNome()
			Method getNome = arg0.getClass().getMethod("getNome");
			
			//chama o m�todo getNome() do primeiro parametro
			s1 = (String) getNome.invoke(arg0);
			
			//chama o m�todo getNome() do segundo parametro
			s2 = (String) getNome.invoke(arg1);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			return -1;
		}
		
		//compara as strings e retorna o resultado
		return s1.compareToIgnoreCase(s2);
	}

}
