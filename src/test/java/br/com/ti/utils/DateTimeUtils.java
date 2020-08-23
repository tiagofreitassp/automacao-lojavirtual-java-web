package br.com.ti.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateTimeUtils {
    private DateTimeUtils() {
        throw new UnsupportedOperationException("CLASSE UTILITÁRIA, NÃO DEVE SER INSTANCIADA");
    }

    public static String getHoraAtual() {
        Instant instante = Instant.now();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss_SSS");
        return df.format(instante.toEpochMilli());
    }

    public static String getDiaAtual() {
        Instant instante = Instant.now();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(instante.toEpochMilli());
    }

    public static String getDtHr() {
        String dia = getDiaAtual().replaceAll("/", "-");
        String hora = getHoraAtual().replaceAll(":", "_");
        return dia + "__" + hora;
    }

    public static String getDtHrFormatListener() {
        Instant instante = Instant.now();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(instante.toEpochMilli());
    }

    public static String getDataFormatadaHomeTratamento() {
        Locale localePtBr = Locale.forLanguageTag("pt-BR");
        ZoneId fusoHorarioSp = ZoneId.of("America/Sao_Paulo");
        LocalDate dataLocal = LocalDate.now(fusoHorarioSp);

        int diaDoMes = dataLocal.getDayOfMonth();
        int ano = dataLocal.getYear();

        String diaDaSemanaFull = dataLocal.getDayOfWeek().getDisplayName(TextStyle.FULL, localePtBr);
        String diaDaSemana = diaDaSemanaFull.split("-")[0];
        String mes = dataLocal.getMonth().getDisplayName(TextStyle.FULL, localePtBr);

        String template = "%s, %d de %s de %d";

        return String.format(template, diaDaSemana, diaDoMes, mes, ano);
    }

    public static LocalDateTime convertStringToLocalDateTime(String dataHr) {
        return LocalDateTime.parse(dataHr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toLocalDateTime();
    }
}
