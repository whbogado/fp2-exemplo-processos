
import java.io.InputStream;


/**
 * IF62C Fundamentos de Programação 2
 * Exemplo de programação em Java.
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 */
public class ExemploExecCmd {

    public static void main(String[] args) throws Exception {
        
        // executamos o comando compatível com o sistema opearacional
        String cmd;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().contains("windows"))
            cmd = "cmd /c c: & cd \\windows\\temp & dir";
        else
            cmd = "sh -c \"cd /tmp;ls -al\"";
        
        byte[] saida = new byte[1024];
        int nRead;
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(cmd);
        InputStream is = proc.getInputStream();
        while (proc.isAlive()) {
            do {
                nRead = is.read(saida);
                System.out.print(new String(saida, 0, nRead));
            } while (is.available() > 0);
        }
        System.out.println("Processo terminado com status " + proc.exitValue());
    }
    
}
