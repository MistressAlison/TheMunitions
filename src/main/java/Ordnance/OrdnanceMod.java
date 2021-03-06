package Ordnance;

import Ordnance.icons.IconContainer;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import Ordnance.cards.cardvars.SecondDamage;
import Ordnance.cards.cardvars.SecondMagicNumber;
import Ordnance.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class OrdnanceMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber {

    public static final String modID = "Ordnance";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static final Color SANGUINE_RED = CardHelper.getColor(188, 63, 74);

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/bg_attack_purplegrad.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/bg_skill_purplegrad.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/bg_power_purplegrad.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/bg_attack_purplegrad.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/bg_skill_purplegrad.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/bg_power_purplegrad.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
//    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack_bg.png";
//    private static final String SKILL_S_ART = modID + "Resources/images/512/skill_bg.png";
//    private static final String POWER_S_ART = modID + "Resources/images/512/power_bg.png";
//    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack_bg.png";
//    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill_bg.png";
//    private static final String POWER_L_ART = modID + "Resources/images/1024/power_bg.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";

    public static final String ENABLE_CARD_BATTLE_TALK_SETTING = "enableCardBattleTalk";
    public static boolean enableCardBattleTalkEffect = false;

    public static final String CARD_BATTLE_TALK_PROBABILITY_SETTING = "cardTalkProbability";
    public static int cardTalkProbability = 10; //Out of 100

    public static final String ENABLE_DAMAGED_BATTLE_TALK_SETTING = "enableDamagedBattleTalk";
    public static boolean enableDamagedBattleTalkEffect = false;

    public static final String DAMAGED_BATTLE_TALK_PROBABILITY_SETTING = "damagedTalkProbability";
    public static int damagedTalkProbability = 20; //Out of 100

    public static final String ENABLE_PRE_BATTLE_TALK_SETTING = "enablePreBattleTalk";
    public static boolean enablePreBattleTalkEffect = false;

    public static final String PRE_BATTLE_TALK_PROBABILITY_SETTING = "preTalkProbability";
    public static int preTalkProbability = 50; //Out of 100

    public OrdnanceMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheMunitions.Enums.SANGUINE_RED_COLOR, SANGUINE_RED, SANGUINE_RED, SANGUINE_RED,
                SANGUINE_RED, SANGUINE_RED, SANGUINE_RED, SANGUINE_RED,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        OrdnanceMod thismod = new OrdnanceMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheMunitions(TheMunitions.characterStrings.NAMES[1], TheMunitions.Enums.THE_MUNITIONS),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheMunitions.Enums.THE_MUNITIONS);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        CustomIconHelper.addCustomIcon(IconContainer.IceIcon.get());
        CustomIconHelper.addCustomIcon(IconContainer.ParalysisIcon.get());
        CustomIconHelper.addCustomIcon(IconContainer.RangedIcon.get());
        CustomIconHelper.addCustomIcon(IconContainer.BleedIcon.get());
        CustomIconHelper.addCustomIcon(IconContainer.FireIcon.get());
        CustomIconHelper.addCustomIcon(IconContainer.ElectricIcon.get());
        CustomIconHelper.addCustomIcon(IconContainer.PoisonIcon.get());
        CustomIconHelper.addCustomIcon(IconContainer.PunctureIcon.get());
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter("Ordnance.cards")
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");

        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/CardModstrings.json");

        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Chatterstrings.json");

        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/DamageModstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
