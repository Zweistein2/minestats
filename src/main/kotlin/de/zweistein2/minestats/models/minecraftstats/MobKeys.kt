package de.zweistein2.minestats.models.minecraftstats

enum class MobKeys(override val jsonName: String): Key {
    COD("minecraft:cod"),
    GUARDIAN("minecraft:guardian"),
    SKELETON_HORSE("minecraft:skeleton_horse"),
    PARROT("minecraft:parrot"),
    ELDER_GUARDIAN("minecraft:elder_guardian"),
    SALMON("minecraft:salmon"),
    ENDER_DRAGON("minecraft:ender_dragon"),
    PILLAGER("minecraft:pillager"),
    DONKEY("minecraft:donkey"),
    ZOMBIE("minecraft:zombie"),
    ENDERMITE("minecraft:endermite"),
    RABBIT("minecraft:rabbit"),
    ZOMBIFIED_PIGLIN("minecraft:zombified_piglin"),
    WITCH("minecraft:witch"),
    SPIDER("minecraft:spider"),
    BEE("minecraft:bee"),
    SILVERFISH("minecraft:silverfish"),
    PIGLIN("minecraft:piglin"),
    ZOMBIE_VILLAGER("minecraft:zombie_villager"),
    SHEEP("minecraft:sheep"),
    TRADER_LLAMA("minecraft:trader_llama"),
    GHAST("minecraft:ghast"),
    SLIME("minecraft:slime"),
    DROWNED("minecraft:drowned"),
    HOGLIN("minecraft:hoglin"),
    SQUID("minecraft:squid"),
    WITHER_SKELETON("minecraft:wither_skeleton"),
    VILLAGER("minecraft:villager"),
    CAVE_SPIDER("minecraft:cave_spider"),
    DOLPHIN("minecraft:dolphin"),
    SHULKER("minecraft:shulker"),
    PUFFERFISH("minecraft:pufferfish"),
    SKELETON("minecraft:skeleton"),
    FOX("minecraft:fox"),
    CHICKEN("minecraft:chicken"),
    CAT("minecraft:cat"),
    MAGMA_CUBE("minecraft:magma_cube"),
    COW("minecraft:cow"),
    HORSE("minecraft:horse"),
    ENDERMAN("minecraft:enderman"),
    WOLF("minecraft:wolf"),
    PIG("minecraft:pig"),
    CREEPER("minecraft:creeper"),
    BLAZE("minecraft:blaze"),
    PHANTOM("minecraft:phantom"),
    PANDA("minecraft:panda"),
    BAT("minecraft:bat"),
    AXOLOTL("minecraft:axolotl"),
    MOOSHROOM("minecraft:mooshroom"),
    GLOW_SQUID("minecraft:glow_squid"),
    MULE("minecraft:mule"),
    OCELOT("minecraft:ocelot"),
    STRIDER("minecraft:strider"),
    TROPICAL_FISH("minecraft:tropical_fish"),
    TURTLE("minecraft:turtle"),
    SNOW_GOLEM("minecraft:snow_golem"),
    GOAT("minecraft:goat"),
    IRON_GOLEM("minecraft:iron_golem"),
    LLAMA("minecraft:llama"),
    EVOKER("minecraft:evoker"),
    HUSK("minecraft:husk"),
    RAVAGER("minecraft:ravager"),
    PIGLIN_BRUTE("minecraft:piglin_brute"),
    STRAY("minecraft:stray"),
    VEX("minecraft:vex"),
    VINDICATOR("minecraft:vindicator"),
    ZOGLIN("minecraft:zoglin"),
    WITHER("minecraft:wither");

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