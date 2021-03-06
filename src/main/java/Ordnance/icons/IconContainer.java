package Ordnance.icons;

import Ordnance.OrdnanceMod;
import Ordnance.util.TexLoader;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

public class IconContainer {

    public static class RangedIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Ranged");
        private static AbstractCustomIcon singleton;

        public RangedIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Ranged.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new RangedIcon();
            }
            return singleton;
        }
    }

    public static class FireIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Fire");
        private static AbstractCustomIcon singleton;

        public FireIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Fire.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new FireIcon();
            }
            return singleton;
        }
    }

    public static class BleedIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Bleed");
        private static AbstractCustomIcon singleton;

        public BleedIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Bleed.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new BleedIcon();
            }
            return singleton;
        }
    }

    public static class ElectricIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Electric");
        private static AbstractCustomIcon singleton;

        public ElectricIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Electric.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new ElectricIcon();
            }
            return singleton;
        }
    }

    public static class IceIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Ice");
        private static AbstractCustomIcon singleton;

        public IceIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Ice.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new IceIcon();
            }
            return singleton;
        }
    }

    public static class ParalysisIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Paralysis");
        private static AbstractCustomIcon singleton;

        public ParalysisIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Paralysis.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new ParalysisIcon();
            }
            return singleton;
        }
    }

    public static class PoisonIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Poison");
        private static AbstractCustomIcon singleton;

        public PoisonIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Poison.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new PoisonIcon();
            }
            return singleton;
        }
    }

    public static class PunctureIcon extends AbstractCustomIcon {
        public static final String ID = OrdnanceMod.makeID("Puncture");
        private static AbstractCustomIcon singleton;

        public PunctureIcon() {
            super(ID, TexLoader.getTexture("OrdnanceResources/images/icons/Puncture.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new PunctureIcon();
            }
            return singleton;
        }
    }
}
