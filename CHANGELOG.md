# 2.4.0 - 1.21.1
- Spell Engine 1.5 Update
- increase berserker Better Combat range to 3.0
- removed old unused spells and their related files
- removed the increased incoming damage from the Rage Buff, because the Berserker already takes big risks going low on health
- added outrage status effect, removed all CustomImpacts, fully using Spell Engine API for the Berserker Spells
- Outrage now consumes the Rage Effect at the End of the Casting
- Buffed Movement Speed while casting Rage
- Add TweaksConfig, so special Weapons can also be used without Better End & Nether and the Aether
- Update to Fabric Loom 1.9

# 2.3.0 - 1.21.1
**- Spell Engine 1.4 Update!**
- AzureLib Armor 3.0 Update!
- Nordic Storm & Bloody Strike range now matching the melee weapon distance
- Updated Berserker Spell Icons (Comissioned Work by SovSeabird)
- **LICENSE CHANGE** All Textures are now under the ARR License

2.2.2 - 1.21
- Update for Spell Scrolls
- Sacrifice Drain, Nordic Storm & Rumbling Swing are disabled as passive spells for the special axes
- The spells will return in the future

2.2.1
- fixed rpg series aether weapon tag

2.2.0
- Spell Engine 1.2 Update
- added the Holy Raid Axe (Aether Variant)

2.1.0
- Added 3rd tier Armor, Netherite Armor for the Berserker Class
- Changed balancing of Armor, to match the new RPG Series balancing
- added all the other Vanilla Material Raid Axes to the Raid Axe Advancement

2.0.3
- Northling Armor used Wildling Armor texture, oops

2.0.2
- Spell Engine API Update (1.1.0 + 1.21.1)
- fixed translation file damage calculating-translation for spells

2.0.1
- outrage cast duration was 20 seconds accidentally, turned back to 4
- increased bloody strike self damage and nerfed the amount of absorption you get (configurable in effects config)

2.0.0
- 1.21.x Update
- new Status Effect, Blood Sacrifice for Bloody Strike due to Absorption logic changes in new minecraft version

1.1.0
Official Release of the Mod!
###GENERAL CHANGES
- tweaked the rage Model a bit because the texture was flickering
- code clean up
- removed the primary group from the special spells
- added a crafting recipe for the Berserker Spell Tome
- made rare & netherite tier Axes fireproof
- updated bloody strike texture

###BALANCING
- added a physical melee berserker spell school, your spells now get enhanced crit chance & damage from your rage attribute
- removed spell crit attributes from frozen and thunder Raid Axe and added Rage Attribute
- again added rage attribute on the Berserker Axes
- changed casting time and spell power coefficient of Sacrifice Drain
- deleted undertow from the Berserker Spell Pool, still available with datapacks
- Bloody Strike now gives Absorption Hearts in trade for your Health instead of Healing, for better synergy with the rage effect & attribute, to stay low on hp
- Outrage now only clears negative effects, if the maximum rage effect amplifier is reached, you also increase the duration of the rage effect if thats the case
- new Spell: Blood Reckoning, healing you and converting your absorption hearts to health, also doubles your active Rage Buff duration
- Wild Rage, Blood Reckoning, Bloody Strike & Outrage now work perfectly together, getting low hp dealing high damage and then healing yourself
- New Spell-Tier Order: 1: Wild Rage 2: Blood Reckoning 3: Bloody Strike 4: Outrage


1.0.8
- Added Compat to BetterEnd & BetterNether, you can craft a Aeternium Raid Axe or a Ruby Raid Axe
- Added Tags for the Berserker Axes

1.0.7
- italian translation thanks to Zano1999
- changed the loot injection for the 3 special weapons to the rpg series tag system

1.0.6
FIXES
- Wild Rage effect gave addition instead of multiply for the Rage Attribute
- fixed something with the Outrage damage calculation and buffed the range
BALANCING
- bloody strike now adds the bleeding effect on non-undead targets
- nerfed hunger costs of the spells
- buffed the damage for all spells
- buffed better combat range of raid axes
- made raid axes attack slower but dealing more damage
- removed the Spell Crit Chance & Spell Crit Damage from the Raid Axes and Berserker Armor
- Buffed the armor values of Berserker Armor and gave them attack speed
- While Soul Devourer is active, you heal yourself on killing enemies ( percentage of their may health, configurable)
VISUALS & ENHANCEMENT
- Updated for Spell Engine version 0.15
- changed Undertow to use new spell engine feature
- walk in normal speed while casting Sacrifice Drain & Outrage
- EnviromentZ & Frostiful Compat for my Berserker Armor
- Crafting with fur Items now functions with tags, to add compat for other fur items
- changed models and textures of raid axes (longer axe then the regular)

1.0.5
- Moved Berserker Spell Cost Damage Source to this mod from More RPG Classes Mod

1.0.4
- changed how Outrage works, now it deals damage according to your rage attribute
- buffed bloody strike, so it heals the player the full amount of the dealt damage
- added russian translation thanks to C'King

1.0.3
- added an effect config file
- added support for german language
- nerfed all spells, made them more exhausting because they don't use items as spell cost
- added advancements for the class
- gave vanilla axes the ability to cast spells

1.0.2
- Experimental Update for new Spell Engine 0.14 Version and Spell Power 0.10 Version
- Changed spell pool and spell assignment for the book and the weapons
- changed the loot system to the RPG Series tag based loot distribution of Spell Engine
- Re-Balanced some Spells
- Re-Balanced some Weapon Attributes

1.0.1
- Update for Spell Engine 0.13.0 compat

1.0.0
- First Release of the Berserker Class Mod
- WIP Beta Version