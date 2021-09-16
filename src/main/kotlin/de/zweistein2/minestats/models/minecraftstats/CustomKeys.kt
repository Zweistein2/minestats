package de.zweistein2.minestats.models.minecraftstats

enum class CustomKeys(override val jsonName: String): Key {
    ANIMALS_BRED("minecraft:animals_bred"),
    BELL_RING("minecraft:bell_ring"),
    DEATHS("minecraft:deaths"),
    DROP("minecraft:drop"),
    EAT_CAKE_SLICE("minecraft:eat_cake_slice"),
    ENCHANT_ITEM("minecraft:enchant_item"),
    FILL_CAULDRON("minecraft:fill_cauldron"),
    FISH_CAUGHT("minecraft:fish_caught"),
    JUMP("minecraft:jump"),
    LEAVE_GAME("minecraft:leave_game"),
    MOB_KILLS("minecraft:mob_kills"),
    PLAYER_KILLS("minecraft:player_kills"),
    PLAY_NOTEBLOCK("minecraft:play_noteblock"),
    PLAY_RECORD("minecraft:play_record"),
    POT_FLOWER("minecraft:pot_flower"),
    RAID_TRIGGER("minecraft:raid_trigger"),
    RAID_WIN("minecraft:raid_win"),
    SLEEP_IN_BED("minecraft:sleep_in_bed"),
    TALKED_TO_VILLAGER("minecraft:talked_to_villager"),
    TARGET_HIT("minecraft:target_hit"),
    TRADED_WITH_VILLAGER("minecraft:traded_with_villager"),
    TRIGGER_TRAPPED_CHEST("minecraft:trigger_trapped_chest"),
    TUNE_NOTEBLOCK("minecraft:tune_noteblock"),

    // Cleans
    CLEAN_ARMOR("minecraft:clean_armor"),
    CLEAN_BANNER("minecraft:clean_banner"),
    CLEAN_SHULKER_BOX("minecraft:clean_shulker_box"),

    // Damage
    DAMAGE_ABSORBED("minecraft:damage_absorbed"),
    DAMAGE_BLOCKED_BY_SHIELD("minecraft:damage_blocked_by_shield"),
    DAMAGE_DEALT("minecraft:damage_dealt"),
    DAMAGE_DEALT_ABSORBED("minecraft:damage_dealt_absorbed"),
    DAMAGE_DEALT_RESISTED("minecraft:damage_dealt_resisted"),
    DAMAGE_RESISTED("minecraft:damage_resisted"),
    DAMAGE_TAKEN("minecraft:damage_taken"),

    // Containers opened
    OPEN_BARREL("minecraft:open_barrel"),
    OPEN_CHEST("minecraft:open_chest"),
    OPEN_ENDERCHEST("minecraft:open_enderchest"),
    OPEN_SHULKER_BOX("minecraft:open_shulker_box"),

    // Times
    PLAY_TIME("minecraft:play_time"),
    SNEAK_TIME("minecraft:sneak_time"),
    TIME_SINCE_DEATH("minecraft:time_since_death"),
    TIME_SINCE_REST("minecraft:time_since_rest"),
    TOTAL_WORLD_TIME("minecraft:total_world_time"),

    // Interactions
    INTERACT_WITH_ANVIL("minecraft:interact_with_anvil"),
    INTERACT_WITH_BEACON("minecraft:interact_with_beacon"),
    INTERACT_WITH_BLAST_FURNACE("minecraft:interact_with_blast_furnace"),
    INTERACT_WITH_BREWINGSTAND("minecraft:interact_with_brewingstand"),
    INTERACT_WITH_CAMPFIRE("minecraft:interact_with_campfire"),
    INTERACT_WITH_CARTOGRAPHY_TABLE("minecraft:interact_with_cartography_table"),
    INTERACT_WITH_CRAFTING_TABLE("minecraft:interact_with_crafting_table"),
    INTERACT_WITH_FURNACE("minecraft:interact_with_furnace"),
    INTERACT_WITH_GRINDSTONE("minecraft:interact_with_grindstone"),
    INTERACT_WITH_LECTERN("minecraft:interact_with_lectern"),
    INTERACT_WITH_LOOM("minecraft:interact_with_loom"),
    INTERACT_WITH_SMITHING_TABLE("minecraft:interact_with_smithing_table"),
    INTERACT_WITH_SMOKER("minecraft:interact_with_smoker"),
    INSPECT_DISPENSER("minecraft:inspect_dispenser"),
    INSPECT_DROPPER("minecraft:inspect_dropper"),
    INSPECT_HOPPER("minecraft:inspect_hopper"),
    INTERACT_WITH_STONECUTTER("minecraft:interact_with_stonecutter"),
    USE_CAULDRON("minecraft:use_cauldron"),

    // Movement
    WALK_ONE_CM("minecraft:walk_one_cm"),
    CROUCH_ONE_CM("minecraft:crouch_one_cm"),
    SPRINT_ONE_CM("minecraft:sprint_one_cm"),
    SWIM_ONE_CM("minecraft:swim_one_cm"),
    FALL_ONE_CM("minecraft:fall_one_cm"),
    CLIMB_ONE_CM("minecraft:climb_one_cm"),
    FLY_ONE_CM("minecraft:fly_one_cm"),
    WALK_UNDER_WATER_ONE_CM("minecraft:walk_under_water_one_cm"),
    WALK_ON_WATER_ONE_CM("minecraft:walk_on_water_one_cm"),
    MINECART_ONE_CM("minecraft:minecart_one_cm"),
    BOAT_ONE_CM("minecraft:boat_one_cm"),
    PIG_ONE_CM("minecraft:pig_one_cm"),
    STRIDER_ONE_CM("minecraft:strider_one_cm"),
    HORSE_ONE_CM("minecraft:horse_one_cm"),
    AVIATE_ONE_CM("minecraft:aviate_one_cm");

    companion object {
        fun valueOfOrNull(name: String): CustomKeys? {
            for (customKey in values()) {
                if (customKey.name == name) {
                    return customKey
                }
            }

            return null
        }
    }
}