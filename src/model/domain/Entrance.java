package model.domain;

public class Entrance {

    private final Participant participant;

    public Entrance(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipant() {
        return participant;
    }

    @Override
    public String toString(){
        return "Ingresso del ".concat(participant.toString());

    }
}
