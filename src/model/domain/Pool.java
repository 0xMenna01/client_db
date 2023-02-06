package model.domain;

public class Pool {

    private final Integer id;

    public Pool(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Vasca (ID: ").append(id.toString()).append(")");
        return  sb.toString();
    }
}
