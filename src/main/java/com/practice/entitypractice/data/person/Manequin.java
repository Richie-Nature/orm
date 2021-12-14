package com.practice.entitypractice.data.person;

import javax.persistence.*;
import java.util.List;

@Entity
public class Manequin extends Humanoid {

    private boolean hasAHead;
    private MannequinShape mannequinShape;

    enum MannequinShape {
        LITHE, MUSCULUR, UNASSUMING;
    }

    public boolean isHasAHead() {
        return hasAHead;
    }

    public void setHasAHead(boolean hasAHead) {
        this.hasAHead = hasAHead;
    }

    public MannequinShape getMannequinShape() {
        return mannequinShape;
    }

    public void setMannequinShape(MannequinShape mannequinShape) {
        this.mannequinShape = mannequinShape;
    }
}
