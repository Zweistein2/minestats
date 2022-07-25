package de.zweistein2.minestats.models.minecraftstats

enum class MobKeys(override val jsonName: String, override val show: Boolean = true,
                   override val tags: List<Tags>, override vararg val alternatives: String): Key {
    COD("minecraft:cod", tags = listOf()),
    GUARDIAN("minecraft:guardian", true, listOf(), "Guardian"),
    SKELETON_HORSE("minecraft:skeleton_horse", true, listOf(), "SkeletonHorse"),
    PARROT("minecraft:parrot", true, listOf(), "Parrot"),
    ELDER_GUARDIAN("minecraft:elder_guardian", true, listOf(), "ElderGuardian"),
    SALMON("minecraft:salmon", tags = listOf()),
    ENDER_DRAGON("minecraft:ender_dragon", true, listOf(), "EnderDragon"),
    PILLAGER("minecraft:pillager", tags = listOf()),
    DONKEY("minecraft:donkey", tags = listOf()),
    ZOMBIE("minecraft:zombie", true, listOf(), "Zombie"),
    ENDERMITE("minecraft:endermite", true, listOf(), "Endermite"),
    RABBIT("minecraft:rabbit", true, listOf(), "Rabbit"),
    ZOMBIFIED_PIGLIN("minecraft:zombified_piglin", tags = listOf()),
    WITCH("minecraft:witch", true, listOf(), "Witch"),
    SPIDER("minecraft:spider", true, listOf(), "Spider"),
    BEE("minecraft:bee", tags = listOf()),
    SILVERFISH("minecraft:silverfish", true, listOf(), "Silverfish"),
    PIGLIN("minecraft:piglin", true, listOf(), "PigZombie"),
    ZOMBIE_VILLAGER("minecraft:zombie_villager", true, listOf(), "ZombieVillager"),
    SHEEP("minecraft:sheep", true, listOf(), "Sheep"),
    TRADER_LLAMA("minecraft:trader_llama", tags = listOf()),
    GHAST("minecraft:ghast", true, listOf(), "Ghast"),
    SLIME("minecraft:slime", true, listOf(), "Slime"),
    DROWNED("minecraft:drowned", tags = listOf()),
    HOGLIN("minecraft:hoglin", tags = listOf()),
    SQUID("minecraft:squid", true, listOf(), "Squid"),
    WITHER_SKELETON("minecraft:wither_skeleton", true, listOf(), "WitherSkeleton"),
    VILLAGER("minecraft:villager", true, listOf(), "Villager"),
    CAVE_SPIDER("minecraft:cave_spider", true, listOf(), "CaveSpider"),
    DOLPHIN("minecraft:dolphin", tags = listOf()),
    SHULKER("minecraft:shulker", true, listOf(), "Shulker"),
    PUFFERFISH("minecraft:pufferfish", tags = listOf()),
    SKELETON("minecraft:skeleton", true, listOf(), "Skeleton"),
    FOX("minecraft:fox", tags = listOf()),
    CHICKEN("minecraft:chicken", true, listOf(), "Chicken"),
    CAT("minecraft:cat", tags = listOf()),
    MAGMA_CUBE("minecraft:magma_cube", tags = listOf()),
    COW("minecraft:cow", true, listOf(), "Cow"),
    HORSE("minecraft:horse", true, listOf(), "Horse", "EntityHorse"),
    ENDERMAN("minecraft:enderman", true, listOf(), "Enderman"),
    WOLF("minecraft:wolf", true, listOf(), "Wolf"),
    PIG("minecraft:pig", true, listOf(), "Pig"),
    CREEPER("minecraft:creeper", true, listOf(), "Creeper"),
    BLAZE("minecraft:blaze", true, listOf(), "Blaze"),
    PHANTOM("minecraft:phantom", tags = listOf()),
    PANDA("minecraft:panda", tags = listOf()),
    BAT("minecraft:bat", true, listOf(), "Bat"),
    AXOLOTL("minecraft:axolotl", tags = listOf()),
    MOOSHROOM("minecraft:mooshroom", true, listOf(), "MushroomCow"),
    GLOW_SQUID("minecraft:glow_squid", tags = listOf()),
    MULE("minecraft:mule", tags = listOf()),
    OCELOT("minecraft:ocelot", true, listOf(), "Ozelot"),
    STRIDER("minecraft:strider", tags = listOf()),
    TROPICAL_FISH("minecraft:tropical_fish", tags = listOf()),
    TURTLE("minecraft:turtle", tags = listOf()),
    SNOW_GOLEM("minecraft:snow_golem", true, listOf(), "SnowMan"),
    GOAT("minecraft:goat", tags = listOf()),
    IRON_GOLEM("minecraft:iron_golem", true, listOf(), "VillagerGolem"),
    LLAMA("minecraft:llama", true, listOf(), "Llama"),
    EVOKER("minecraft:evoker", true, listOf(), "EvocationIllager"),
    HUSK("minecraft:husk", true, listOf(), "Husk"),
    RAVAGER("minecraft:ravager", tags = listOf()),
    PIGLIN_BRUTE("minecraft:piglin_brute", tags = listOf()),
    STRAY("minecraft:stray", true, listOf(), "Stray"),
    VEX("minecraft:vex", true, listOf(), "Vex"),
    VINDICATOR("minecraft:vindicator", true, listOf(), "VindicationIllager"),
    ZOGLIN("minecraft:zoglin", tags = listOf()),
    WITHER("minecraft:wither", true, listOf(), "WitherBoss"),
    ALLAY("minecraft:allay", tags = listOf()),
    FROG("minecraft:frog", tags = listOf()),
    TADPOLE("minecraft:tadpole", tags = listOf()),
    WARDEN("minecraft:warden", tags = listOf());

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