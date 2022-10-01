public class Trans {
    int origin, destiny;
    char symbol;

    public Trans (int origin, int destiny, char symbol)
    {
        this.destiny = destiny;
        this.origin = origin;
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + origin;
        result = prime * result + destiny;
        result = prime * result + (int) symbol;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;

        if (obj == null) 
            return false;

        if (getClass() != obj.getClass())
            return false;

        Trans other = (Trans) obj;
        if (origin != other.origin)
            return false;
        
        if (destiny != other.destiny)
            return false;

        if (symbol != other.symbol)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return String.format("( %d, %c, %d )", this.origin, this.symbol, this.destiny);
    }
    
}
