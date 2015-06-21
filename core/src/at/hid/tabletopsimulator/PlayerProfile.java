/**
 * 
 */
package at.hid.tabletopsimulator;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author dunkler_engel
 *
 */
public class PlayerProfile {
	
	private String name;
	
	private int skillPoints = 10, str = 0, vit = 0, att = 0, def = 0, dex = 0, wis = 0, int_ = 0, cha = 0, wil = 0, awa = 0, lck = 0, hp = 100, mp = 50, ap = 7, exp = 0, lvl = 1, gold = 100;
	
	public PlayerProfile() {	
	}

	/**
	 * gets the character name 
	 * @return the character name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the character name
	 * @param name the character name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the skillPoints
	 */
	public int getSkillPoints() {
		return skillPoints;
	}

	/**
	 * @param skillPoints the skillPoints to set
	 */
	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}

	/**
	 * @return the str
	 */
	public int getStr() {
		return str;
	}

	/**
	 * @param str the str to set
	 */
	public void setStr(int str) {
		this.str = str;
	}

	/**
	 * @return the vit
	 */
	public int getVit() {
		return vit;
	}

	/**
	 * @param vit the vit to set
	 */
	public void setVit(int vit) {
		this.vit = vit;
	}

	/**
	 * @return the att
	 */
	public int getAtt() {
		return att;
	}

	/**
	 * @param att the att to set
	 */
	public void setAtt(int att) {
		this.att = att;
	}

	/**
	 * @return the def
	 */
	public int getDef() {
		return def;
	}

	/**
	 * @param def the def to set
	 */
	public void setDef(int def) {
		this.def = def;
	}

	/**
	 * @return the dex
	 */
	public int getDex() {
		return dex;
	}

	/**
	 * @param dex the dex to set
	 */
	public void setDex(int dex) {
		this.dex = dex;
	}

	/**
	 * @return the wis
	 */
	public int getWis() {
		return wis;
	}

	/**
	 * @param wis the wis to set
	 */
	public void setWis(int wis) {
		this.wis = wis;
	}

	/**
	 * @return the int_
	 */
	public int getInt_() {
		return int_;
	}

	/**
	 * @param int_ the int_ to set
	 */
	public void setInt_(int int_) {
		this.int_ = int_;
	}

	/**
	 * @return the cha
	 */
	public int getCha() {
		return cha;
	}

	/**
	 * @param cha the cha to set
	 */
	public void setCha(int cha) {
		this.cha = cha;
	}

	/**
	 * @return the wil
	 */
	public int getWil() {
		return wil;
	}

	/**
	 * @param wil the wil to set
	 */
	public void setWil(int wil) {
		this.wil = wil;
	}

	/**
	 * @return the awa
	 */
	public int getAwa() {
		return awa;
	}

	/**
	 * @param awa the awa to set
	 */
	public void setAwa(int awa) {
		this.awa = awa;
	}

	/**
	 * @return the lck
	 */
	public int getLck() {
		return lck;
	}

	/**
	 * @param lck the lck to set
	 */
	public void setLck(int lck) {
		this.lck = lck;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the mp
	 */
	public int getMp() {
		return mp;
	}

	/**
	 * @param mp the mp to set
	 */
	public void setMp(int mp) {
		this.mp = mp;
	}

	/**
	 * @return the ap
	 */
	public int getAp() {
		return ap;
	}

	/**
	 * @param ap the ap to set
	 */
	public void setAp(int ap) {
		this.ap = ap;
	}

	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}

	/**
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	/**
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * @param gold the gold to set
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * saves the playerprofile to the cloud
	 * @param name the name of the target playerprofile
	 */
	public void saveProfile(String name) {
		JSONObject json = new JSONObject();
		try {
			json.put("name", getName());
			json.put("skillPoints", getSkillPoints());
			json.put("str", getStr());
			json.put("vit", getVit());
			json.put("att", getAtt());
			json.put("def", getDef());
			json.put("dex", getDex());
			json.put("wis", getWis());
			json.put("int", getInt_());
			json.put("cha", getCha());
			json.put("wil", getWil());
			json.put("awa", getAwa());
			json.put("lck", getLck());
			json.put("hp", getHp());
			json.put("mp", getMp());
			json.put("ap", getAp());
			json.put("exp", getExp());
			json.put("lvl", getLvl());
			json.put("gold", getGold());
		} catch (JSONException e) {
			TableTopSimulator.error(this.getClass().toString(), "error creating profile JSONObject", e);
		}
		TableTopSimulator.app42.storageServiceUpdateDocumentByKeyValue(TableTopSimulator.TITLE, "heroes", "name", name, json);
	}
	
	/**
	 * saves the playerprofile to the cloud
	 */
	public void saveProfile() {
		JSONObject json = new JSONObject();
		try {
			json.put("name", getName());
			json.put("skillPoints", getSkillPoints());
			json.put("str", getStr());
			json.put("vit", getVit());
			json.put("att", getAtt());
			json.put("def", getDef());
			json.put("dex", getDex());
			json.put("wis", getWis());
			json.put("int", getInt_());
			json.put("cha", getCha());
			json.put("wil", getWil());
			json.put("awa", getAwa());
			json.put("lck", getLck());
			json.put("hp", getHp());
			json.put("mp", getMp());
			json.put("ap", getAp());
			json.put("exp", getExp());
			json.put("lvl", getLvl());
			json.put("gold", getGold());
		} catch (JSONException e) {
			TableTopSimulator.error(this.getClass().toString(), "error creating profile JSONObject", e);
		}
		TableTopSimulator.app42.storageServiceUpdateDocumentByKeyValue(TableTopSimulator.TITLE, "heroes", "name", TableTopSimulator.playerprofile.getName(), json);
	}
	
	/**
	 * loads the profile from disk
	 * @param saveDir name of the save directory
	 * @return the PlayerProfile object
	 */
	public boolean loadProfile(String name) {
		try {
			TableTopSimulator.app42.storageServiceFindDocumentByKeyValue(TableTopSimulator.TITLE, "heroes", "name", name);
			loadProfileFromJsonobject(new JSONObject(TableTopSimulator.app42.storageGetJsonDoc()));
			return true;
		} catch (Exception e) {
			TableTopSimulator.error(this.getClass().toString(), "error loading profile", e);
			return false;
		}
	}

	/**
	 * @param json
	 */
	public boolean loadProfileFromJsonobject(JSONObject json) {
		try {
			TableTopSimulator.playerprofile.setName(json.getString("name"));
			TableTopSimulator.playerprofile.setSkillPoints(json.getInt("skillPoints"));
			TableTopSimulator.playerprofile.setStr(json.getInt("str"));
			TableTopSimulator.playerprofile.setVit(json.getInt("vit"));
			TableTopSimulator.playerprofile.setAtt(json.getInt("att"));
			TableTopSimulator.playerprofile.setDef(json.getInt("def"));
			TableTopSimulator.playerprofile.setDex(json.getInt("dex"));
			TableTopSimulator.playerprofile.setWis(json.getInt("wis"));
			TableTopSimulator.playerprofile.setInt_(json.getInt("int"));
			TableTopSimulator.playerprofile.setCha(json.getInt("cha"));
			TableTopSimulator.playerprofile.setWil(json.getInt("wil"));
			TableTopSimulator.playerprofile.setAwa(json.getInt("awa"));
			TableTopSimulator.playerprofile.setLck(json.getInt("lck"));
			TableTopSimulator.playerprofile.setHp(json.getInt("hp"));
			TableTopSimulator.playerprofile.setMp(json.getInt("mp"));
			TableTopSimulator.playerprofile.setAp(json.getInt("ap"));
			TableTopSimulator.playerprofile.setExp(json.getInt("exp"));
			TableTopSimulator.playerprofile.setLvl(json.getInt("lvl"));
			TableTopSimulator.playerprofile.setGold(json.getInt("gold"));
			
			return true;
		} catch (JSONException e) {
			TableTopSimulator.error(this.getClass().toString(), "error reading profile JSONbject", e);
			return false;
		}
	}
}
