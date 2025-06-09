package org.lesson10.additionalTask;

//"src/main/java/org/lesson10/additionalTask/files"

public class FileLoggerConfiguration {
    private String logFilePath;
    private LoggingLevel loggingLevel;
    private long maxFileSize;
    private String format;
    private String BASE_PATH;

    public FileLoggerConfiguration(String BASE_PATH, LoggingLevel loggingLevel, long maxFileSize, String format) {
        this.loggingLevel = loggingLevel;
        this.maxFileSize = maxFileSize;
        this.format = format;
        this.BASE_PATH = BASE_PATH;
    }

    public FileLoggerConfiguration() {

    }

    public String getBASE_PATH(){
        return BASE_PATH;
    }

    public void setBASE_PATH(String BASE_PATH) {
        this.BASE_PATH = BASE_PATH;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    public void setLoggingLevel(LoggingLevel loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
