package com.fusionflux.portalcubed.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PortalCubedSounds {

	public static final Identifier GEL_BOUNCE = new Identifier("portalcubed:gelbounce");
	public static final Identifier GEL_RUN = new Identifier("portalcubed:gelrun");
	public static final Identifier PORTAL_GUN_PRIMARY = new Identifier("portalcubed:portalgunprimary");
	public static final Identifier PORTAL_GUN_SECONDARY = new Identifier("portalcubed:portalgunsecondary");
	public static final Identifier PORTAL_INVALID_SURFACE = new Identifier("portalcubed:portal_invalid_surface");
	public static final Identifier PORTAL_AMBIANCE = new Identifier("portalcubed:portalambiance");
	public static final Identifier PORTAL_ENTER = new Identifier("portalcubed:portalenter");
	public static final Identifier PORTAL_EXIT = new Identifier("portalcubed:portalexit");
	public static final Identifier PORTAL_OPEN = new Identifier("portalcubed:portal_open");
	public static final Identifier PORTAL_CLOSE = new Identifier("portalcubed:portal_close");
	public static final Identifier CUBE_HIT_HIGH = new Identifier("portalcubed:cubehithigh");
	public static final Identifier CUBE_HIT_LOW = new Identifier("portalcubed:cubehitlow");
	public static final Identifier CUBE_SCRAPE = new Identifier("portalcubed:cubescrape");
	public static final Identifier COMPANION_CUBE_AMBIANCE = new Identifier("portalcubed:companioncubeambiance");
	public static final Identifier RADIO_MUSIC = new Identifier("portalcubed:radio");
	public static final Identifier EXILE_SONG = new Identifier("portalcubed:exile_vilify");
	public static final Identifier CURIOSITY_CORE_SOUND = new Identifier("portalcubed:curiosity_core");
	public static final Identifier ANGER_CORE_SOUND = new Identifier("portalcubed:anger_core");
	public static final Identifier CAKE_CORE_SOUND = new Identifier("portalcubed:cake_core");

	public static final Identifier SPACE_CORE_SOUND = new Identifier("portalcubed:space_core");
	public static final Identifier FACT_CORE_SOUND = new Identifier("portalcubed:fact_core");
	public static final Identifier ADVENTURE_CORE_SOUND = new Identifier("portalcubed:adventure_core");

	public static final SoundEvent GEL_BOUNCE_EVENT = new SoundEvent(GEL_BOUNCE);
	public static final SoundEvent GEL_RUN_EVENT = new SoundEvent(GEL_RUN);
	public static final SoundEvent PORTAL_AMBIANT_EVENT = new SoundEvent(PORTAL_AMBIANCE);
	public static final SoundEvent FIRE_EVENT_PRIMARY = new SoundEvent(PORTAL_GUN_PRIMARY);
	public static final SoundEvent FIRE_EVENT_SECONDARY = new SoundEvent(PORTAL_GUN_SECONDARY);
	public static final SoundEvent INVALID_PORTAL_EVENT = new SoundEvent(PORTAL_INVALID_SURFACE);
	public static final SoundEvent ENTITY_ENTER_PORTAL = new SoundEvent(PORTAL_ENTER);
	public static final SoundEvent ENTITY_EXIT_PORTAL = new SoundEvent(PORTAL_EXIT);
	public static final SoundEvent ENTITY_PORTAL_OPEN = new SoundEvent(PORTAL_OPEN);
	public static final SoundEvent ENTITY_PORTAL_CLOSE = new SoundEvent(PORTAL_CLOSE);
	public static final SoundEvent CUBE_HIGH_HIT_EVENT = new SoundEvent(CUBE_HIT_HIGH);
	public static final SoundEvent CUBE_LOW_HIT_EVENT = new SoundEvent(CUBE_HIT_LOW);
	public static final SoundEvent CUBE_SCRAPE_EVENT = new SoundEvent(CUBE_SCRAPE);
	public static final SoundEvent COMPANION_CUBE_AMBIANCE_EVENT = new SoundEvent(COMPANION_CUBE_AMBIANCE);
	public static final SoundEvent RADIO_MUSIC_EVENT = new SoundEvent(RADIO_MUSIC);
	public static final SoundEvent EXILE_MUSIC_EVENT = new SoundEvent(EXILE_SONG);
	public static final SoundEvent CURIOSITY_CORE_EVENT = new SoundEvent(CURIOSITY_CORE_SOUND);
	public static final SoundEvent ANGER_CORE_EVENT = new SoundEvent(ANGER_CORE_SOUND);
	public static final SoundEvent CAKE_CORE_EVENT = new SoundEvent(CAKE_CORE_SOUND);
	public static final SoundEvent SPACE_CORE_EVENT = new SoundEvent(SPACE_CORE_SOUND);
	public static final SoundEvent FACT_CORE_EVENT = new SoundEvent(FACT_CORE_SOUND);
	public static final SoundEvent ADVENTURE_CORE_EVENT = new SoundEvent(ADVENTURE_CORE_SOUND);

	public static void registerSounds() {
		Registry.register(Registry.SOUND_EVENT, GEL_BOUNCE, GEL_BOUNCE_EVENT);
		Registry.register(Registry.SOUND_EVENT, GEL_RUN, GEL_RUN_EVENT);
		Registry.register(Registry.SOUND_EVENT, PORTAL_AMBIANCE, PORTAL_AMBIANT_EVENT);
		Registry.register(Registry.SOUND_EVENT, PORTAL_GUN_PRIMARY, FIRE_EVENT_PRIMARY);
		Registry.register(Registry.SOUND_EVENT, PORTAL_GUN_SECONDARY, FIRE_EVENT_SECONDARY);
		Registry.register(Registry.SOUND_EVENT, PORTAL_INVALID_SURFACE, INVALID_PORTAL_EVENT);
		Registry.register(Registry.SOUND_EVENT, PORTAL_ENTER, ENTITY_ENTER_PORTAL);
		Registry.register(Registry.SOUND_EVENT, PORTAL_EXIT, ENTITY_EXIT_PORTAL);
		Registry.register(Registry.SOUND_EVENT, PORTAL_OPEN, ENTITY_PORTAL_OPEN);
		Registry.register(Registry.SOUND_EVENT, PORTAL_CLOSE, ENTITY_PORTAL_CLOSE);
		Registry.register(Registry.SOUND_EVENT, CUBE_HIT_HIGH, CUBE_HIGH_HIT_EVENT);
		Registry.register(Registry.SOUND_EVENT, CUBE_HIT_LOW, CUBE_LOW_HIT_EVENT);
		Registry.register(Registry.SOUND_EVENT, CUBE_SCRAPE, CUBE_SCRAPE_EVENT);
		Registry.register(Registry.SOUND_EVENT, EXILE_SONG, EXILE_MUSIC_EVENT);

		Registry.register(Registry.SOUND_EVENT, COMPANION_CUBE_AMBIANCE, COMPANION_CUBE_AMBIANCE_EVENT);
		Registry.register(Registry.SOUND_EVENT, RADIO_MUSIC, RADIO_MUSIC_EVENT);
		Registry.register(Registry.SOUND_EVENT, CURIOSITY_CORE_SOUND, CURIOSITY_CORE_EVENT);
		Registry.register(Registry.SOUND_EVENT, ANGER_CORE_SOUND, ANGER_CORE_EVENT);
		Registry.register(Registry.SOUND_EVENT, CAKE_CORE_SOUND, CAKE_CORE_EVENT);

		Registry.register(Registry.SOUND_EVENT, SPACE_CORE_SOUND, SPACE_CORE_EVENT);
		Registry.register(Registry.SOUND_EVENT, FACT_CORE_SOUND, FACT_CORE_EVENT);
		Registry.register(Registry.SOUND_EVENT, ADVENTURE_CORE_SOUND, ADVENTURE_CORE_EVENT);
	}

}
