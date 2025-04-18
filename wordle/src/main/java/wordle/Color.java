package wordle;

public class Color {
    
    public String ClearColor(){
        return "\033[0m";
    }

    public String getGreen(){
        return "\033[32m";
    }

    public String getRed(){
        return "\033[31m";
    }

    public String getGray(){
        return "\033[90m";
    }

    public String getYellow(){
        return "\033[33m";
    }

}
