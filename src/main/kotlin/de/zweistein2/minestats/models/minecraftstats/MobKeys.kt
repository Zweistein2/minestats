package de.zweistein2.minestats.models.minecraftstats

enum class MobKeys(override val jsonName: String, override vararg val alternatives: String): Key {
    COD("minecraft:cod"),
    GUARDIAN("minecraft:guardian", "Guardian"),
    SKELETON_HORSE("minecraft:skeleton_horse", "SkeletonHorse"),
    PARROT("minecraft:parrot", "Parrot"),
    ELDER_GUARDIAN("minecraft:elder_guardian", "ElderGuardian"),
    SALMON("minecraft:salmon"),
    ENDER_DRAGON("minecraft:ender_dragon", "EnderDragon"),
    PILLAGER("minecraft:pillager"),
    DONKEY("minecraft:donkey"),
    ZOMBIE("minecraft:zombie", "Zombie"),
    ENDERMITE("minecraft:endermite", "Endermite"),
    RABBIT("minecraft:rabbit", "Rabbit"),
    ZOMBIFIED_PIGLIN("minecraft:zombified_piglin"),
    WITCH("minecraft:witch", "Witch"),
    SPIDER("minecraft:spider", "Spider"),
    BEE("minecraft:bee"),
    SILVERFISH("minecraft:silverfish", "Silverfish"),
    PIGLIN("minecraft:piglin", "PigZombie"),
    ZOMBIE_VILLAGER("minecraft:zombie_villager", "ZombieVillager"),
    SHEEP("minecraft:sheep", "Sheep"),
    TRADER_LLAMA("minecraft:trader_llama"),
    GHAST("minecraft:ghast", "Ghast"),
    SLIME("minecraft:slime", "Slime"),
    DROWNED("minecraft:drowned"),
    HOGLIN("minecraft:hoglin"),
    SQUID("minecraft:squid", "Squid"),
    WITHER_SKELETON("minecraft:wither_skeleton", "WitherSkeleton"),
    VILLAGER("minecraft:villager", "Villager"),
    CAVE_SPIDER("minecraft:cave_spider", "CaveSpider"),
    DOLPHIN("minecraft:dolphin"),
    SHULKER("minecraft:shulker", "Shulker"),
    PUFFERFISH("minecraft:pufferfish"),
    SKELETON("minecraft:skeleton", "Skeleton"),
    FOX("minecraft:fox"),
    CHICKEN("minecraft:chicken", "Chicken"),
    CAT("minecraft:cat"),
    MAGMA_CUBE("minecraft:magma_cube"),
    COW("minecraft:cow", "Cow"),
    HORSE("minecraft:horse", "Horse", "EntityHorse"),
    ENDERMAN("minecraft:enderman", "Enderman"),
    WOLF("minecraft:wolf", "Wolf"),
    PIG("minecraft:pig", "Pig"),
    CREEPER("minecraft:creeper", "Creeper"),
    BLAZE("minecraft:blaze", "Blaze"),
    PHANTOM("minecraft:phantom"),
    PANDA("minecraft:panda"),
    BAT("minecraft:bat", "Bat"),
    AXOLOTL("minecraft:axolotl"),
    MOOSHROOM("minecraft:mooshroom", "MushroomCow"),
    GLOW_SQUID("minecraft:glow_squid"),
    MULE("minecraft:mule"),
    OCELOT("minecraft:ocelot", "Ozelot"),
    STRIDER("minecraft:strider"),
    TROPICAL_FISH("minecraft:tropical_fish"),
    TURTLE("minecraft:turtle"),
    SNOW_GOLEM("minecraft:snow_golem", "SnowMan"),
    GOAT("minecraft:goat"),
    IRON_GOLEM("minecraft:iron_golem", "VillagerGolem"),
    LLAMA("minecraft:llama", "Llama"),
    EVOKER("minecraft:evoker", "EvocationIllager"),
    HUSK("minecraft:husk", "Husk"),
    RAVAGER("minecraft:ravager"),
    PIGLIN_BRUTE("minecraft:piglin_brute"),
    STRAY("minecraft:stray", "Stray"),
    VEX("minecraft:vex", "Vex"),
    VINDICATOR("minecraft:vindicator", "VindicationIllager"),
    ZOGLIN("minecraft:zoglin"),
    WITHER("minecraft:wither", "WitherBoss");

    companion object {
        fun valueOfOrNull(name: String): MobKeys? {
            for (mobKey in values()) {
                if (mobKey.name == name) {
                    return mobKey
                }
            }

            return null
        }
    }
}