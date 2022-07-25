package de.zweistein2.minestats.models.minecraftstats

import de.zweistein2.minestats.models.minecraftstats.Tags.*

enum class CustomKeys(override val jsonName: String, override val show: Boolean = true,
                      override val tags: List<Tags>, override vararg val alternatives: String): Key {

    ANIMALS_BRED("minecraft:animals_bred", true, listOf(MISCELLANEOUS), "animalsBred"),
    BELL_RING("minecraft:bell_ring", tags = listOf(MISCELLANEOUS)),
    DEATHS("minecraft:deaths", true, listOf(MISCELLANEOUS), "deaths"),
    DROP("minecraft:drop", true, listOf(MISCELLANEOUS), "drop"),
    EAT_CAKE_SLICE("minecraft:eat_cake_slice", true, listOf(MISCELLANEOUS), "cakeSlicesEaten"),
    ENCHANT_ITEM("minecraft:enchant_item", true, listOf(MISCELLANEOUS), "itemEnchanted"),
    FISH_CAUGHT("minecraft:fish_caught", true, listOf(MISCELLANEOUS), "fishCaught"),
    JUMP("minecraft:jump", true, listOf(MISCELLANEOUS), "jump"),
    LEAVE_GAME("minecraft:leave_game", true, listOf(MISCELLANEOUS), "leaveGame"),
    MOB_KILLS("minecraft:mob_kills", true, listOf(MISCELLANEOUS), "mobKills"),
    PLAYER_KILLS("minecraft:player_kills", true, listOf(MISCELLANEOUS), "playerKills"),
    PLAY_NOTEBLOCK("minecraft:play_noteblock", true, listOf(MISCELLANEOUS), "noteblockPlayed"),
    PLAY_RECORD("minecraft:play_record", true, listOf(MISCELLANEOUS), "recordPlayed"),
    POT_FLOWER("minecraft:pot_flower", true, listOf(MISCELLANEOUS), "flowerPotted"),
    RAID_TRIGGER("minecraft:raid_trigger", tags = listOf(MISCELLANEOUS)),
    RAID_WIN("minecraft:raid_win", tags = listOf(MISCELLANEOUS)),
    SLEEP_IN_BED("minecraft:sleep_in_bed", true, listOf(MISCELLANEOUS), "sleepInBed"),
    TALKED_TO_VILLAGER("minecraft:talked_to_villager", true, listOf(MISCELLANEOUS), "talkedToVillager"),
    TARGET_HIT("minecraft:target_hit", tags = listOf(MISCELLANEOUS)),
    TRADED_WITH_VILLAGER("minecraft:traded_with_villager", true, listOf(MISCELLANEOUS), "tradedWithVillager"),
    TRIGGER_TRAPPED_CHEST("minecraft:trigger_trapped_chest", true, listOf(MISCELLANEOUS), "trappedChestTriggered"),
    TUNE_NOTEBLOCK("minecraft:tune_noteblock", true, listOf(MISCELLANEOUS), "noteblockTuned"),

    // Cleaning
    CLEAN_ARMOR("minecraft:clean_armor", true, listOf(CLEAN), "armorCleaned"),
    CLEAN_BANNER("minecraft:clean_banner", true, listOf(CLEAN), "bannerCleaned"),
    CLEAN_SHULKER_BOX("minecraft:clean_shulker_box", tags = listOf(CLEAN)),

    // Damage
    DAMAGE_ABSORBED("minecraft:damage_absorbed", tags = listOf(DAMAGE)),
    DAMAGE_BLOCKED_BY_SHIELD("minecraft:damage_blocked_by_shield", tags = listOf(DAMAGE)),
    DAMAGE_DEALT("minecraft:damage_dealt", true, listOf(DAMAGE), "damageDealt"),
    DAMAGE_DEALT_ABSORBED("minecraft:damage_dealt_absorbed", tags = listOf(DAMAGE)),
    DAMAGE_DEALT_RESISTED("minecraft:damage_dealt_resisted", tags = listOf(DAMAGE)),
    DAMAGE_RESISTED("minecraft:damage_resisted", tags = listOf(DAMAGE)),
    DAMAGE_TAKEN("minecraft:damage_taken", true, listOf(DAMAGE), "damageTaken"),

    // Containers opened
    OPEN_BARREL("minecraft:open_barrel", tags = listOf(CONTAINER)),
    OPEN_CHEST("minecraft:open_chest", true, listOf(CONTAINER), "chestOpened"),
    OPEN_ENDERCHEST("minecraft:open_enderchest", true, listOf(CONTAINER), "enderchestOpened"),
    OPEN_SHULKER_BOX("minecraft:open_shulker_box", true, listOf(CONTAINER), "shulkerBoxOpened"),

    // Times
    PLAY_TIME("minecraft:play_time", true, listOf(TIME), "playOneMinute"),
    SNEAK_TIME("minecraft:sneak_time", true, listOf(TIME), "sneakTime"),
    TIME_SINCE_DEATH("minecraft:time_since_death", true, listOf(TIME), "timeSinceDeath"),
    TIME_SINCE_REST("minecraft:time_since_rest", tags = listOf(TIME)),
    TOTAL_WORLD_TIME("minecraft:total_world_time", true, listOf(TIME), "minecraft:play_one_minute", "playOneMinute"),

    // Interactions
    INTERACT_WITH_ANVIL("minecraft:interact_with_anvil", tags = listOf(INTERACTION)),
    INTERACT_WITH_BEACON("minecraft:interact_with_beacon", true, listOf(INTERACTION), "beaconInteraction"),
    INTERACT_WITH_BLAST_FURNACE("minecraft:interact_with_blast_furnace", tags = listOf(INTERACTION)),
    INTERACT_WITH_BREWINGSTAND("minecraft:interact_with_brewingstand", true, listOf(INTERACTION),
        "brewingstandInteraction"),
    INTERACT_WITH_CAMPFIRE("minecraft:interact_with_campfire", tags = listOf(INTERACTION)),
    INTERACT_WITH_CARTOGRAPHY_TABLE("minecraft:interact_with_cartography_table", tags = listOf(INTERACTION)),
    INTERACT_WITH_CRAFTING_TABLE("minecraft:interact_with_crafting_table", true, listOf(INTERACTION),
        "craftingTableInteraction"),
    INTERACT_WITH_FURNACE("minecraft:interact_with_furnace", true, listOf(INTERACTION), "furnaceInteraction"),
    INTERACT_WITH_GRINDSTONE("minecraft:interact_with_grindstone", tags = listOf(INTERACTION)),
    INTERACT_WITH_LECTERN("minecraft:interact_with_lectern", tags = listOf(INTERACTION)),
    INTERACT_WITH_LOOM("minecraft:interact_with_loom", tags = listOf(INTERACTION)),
    INTERACT_WITH_SMITHING_TABLE("minecraft:interact_with_smithing_table", tags = listOf(INTERACTION)),
    INTERACT_WITH_SMOKER("minecraft:interact_with_smoker", tags = listOf(INTERACTION)),
    INSPECT_DISPENSER("minecraft:inspect_dispenser", true, listOf(INTERACTION), "dispenserInspected"),
    INSPECT_DROPPER("minecraft:inspect_dropper", true, listOf(INTERACTION), "dropperInspected"),
    INSPECT_HOPPER("minecraft:inspect_hopper", true, listOf(INTERACTION), "hopperInspected"),
    INTERACT_WITH_STONECUTTER("minecraft:interact_with_stonecutter", tags = listOf(INTERACTION)),
    USE_CAULDRON("minecraft:use_cauldron", true, listOf(INTERACTION), "cauldronUsed"),
    FILL_CAULDRON("minecraft:fill_cauldron", true, listOf(INTERACTION), "cauldronFilled"),

    // Movement
    WALK_ONE_CM("minecraft:walk_one_cm", true, listOf(MOVEMENT), "walkOneCm"),
    CROUCH_ONE_CM("minecraft:crouch_one_cm", true, listOf(MOVEMENT), "crouchOneCm"),
    SPRINT_ONE_CM("minecraft:sprint_one_cm", true, listOf(MOVEMENT), "sprintOneCm"),
    SWIM_ONE_CM("minecraft:swim_one_cm", true, listOf(MOVEMENT), "swimOneCm"),
    FALL_ONE_CM("minecraft:fall_one_cm", true, listOf(MOVEMENT), "fallOneCm"),
    CLIMB_ONE_CM("minecraft:climb_one_cm", true, listOf(MOVEMENT), "climbOneCm"),
    FLY_ONE_CM("minecraft:fly_one_cm", true, listOf(MOVEMENT), "flyOneCm"),
    WALK_UNDER_WATER_ONE_CM("minecraft:walk_under_water_one_cm", true, listOf(MOVEMENT), "diveOneCm"),
    WALK_ON_WATER_ONE_CM("minecraft:walk_on_water_one_cm", tags = listOf(MOVEMENT)),
    MINECART_ONE_CM("minecraft:minecart_one_cm", true, listOf(MOVEMENT), "minecartOneCm"),
    BOAT_ONE_CM("minecraft:boat_one_cm", true, listOf(MOVEMENT), "boatOneCm"),
    PIG_ONE_CM("minecraft:pig_one_cm", true, listOf(MOVEMENT), "pigOneCmpigOneCm"),
    STRIDER_ONE_CM("minecraft:strider_one_cm", tags = listOf(MOVEMENT)),
    HORSE_ONE_CM("minecraft:horse_one_cm", true, listOf(MOVEMENT), "horseOneCm"),
    AVIATE_ONE_CM("minecraft:aviate_one_cm", true, listOf(MOVEMENT), "aviateOneCm");

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