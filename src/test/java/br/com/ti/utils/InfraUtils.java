package br.com.ti.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.logging.Logger;

public class InfraUtils {
    private InfraUtils() {
        throw new UnsupportedOperationException("CLASSE UTILITÁRIA, NÃO DEVE SER INSTANCIADA");
    }

    /**
     * Este método retorna o ip da máquina de execução do teste
     *
     * @author Tiago Freitas
     * @return
     * @since 23/08/2020
     */
    public static String getIpClient() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception exception) {
            Logger.getLogger(exception.getMessage());
        }
        return null;
    }

    /**
     * Este método retorna o nome do computador
     *
     * @author Tiago Freitas
     * @return
     * @since 23/08/2020
     */
    public static String getPcName() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
        return null;
    }

    /**
     * Este método o usuário logado na máquina de execução do teste
     *
     * @author Tiago Freitas
     * @return
     * @since 23/08/2020
     */
    public static String getWindowsUser() {
        return System.getProperty("user.name");
    }


    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static String getExecutionProcessConsoleOutput(Process process) throws IOException {
        String lines = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while(true) {
            String actualLine = reader.readLine();
            if(actualLine == null) break;
            lines += actualLine +"\n";
        }
        reader.close();
        return lines;
    }
}
