package de.zweistein2.minestats.models.minecraftstats

enum class ItemKeys(override val jsonName: String, override vararg val alternatives: String): Key {
    IRON_SWORD("minecraft:iron_sword"),
    IRON_SHOVEL("minecraft:iron_shovel"),
    IRON_PICKAXE("minecraft:iron_pickaxe"),
    IRON_AXE("minecraft:iron_axe"),
    FLINT_AND_STEEL("minecraft:flint_and_steel"),
    APPLE("minecraft:apple"),
    BOW("minecraft:bow"),
    ARROW("minecraft:arrow"),
    COAL("minecraft:coal"),
    CHARCOAL("minecraft:charcoal"),
    DIAMOND("minecraft:diamond"),
    IRON_INGOT("minecraft:iron_ingot"),
    GOLD_INGOT("minecraft:gold_ingot"),
    WOODEN_SWORD("minecraft:wooden_sword"),
    WOODEN_SHOVEL("minecraft:wooden_shovel"),
    WOODEN_PICKAXE("minecraft:wooden_pickaxe"),
    WOODEN_AXE("minecraft:wooden_axe"),
    STONE_SWORD("minecraft:stone_sword"),
    STONE_SHOVEL("minecraft:stone_shovel"),
    STONE_PICKAXE("minecraft:stone_pickaxe"),
    STONE_AXE("minecraft:stone_axe"),
    DIAMOND_SWORD("minecraft:diamond_sword"),
    DIAMOND_SHOVEL("minecraft:diamond_shovel"),
    DIAMOND_PICKAXE("minecraft:diamond_pickaxe"),
    DIAMOND_AXE("minecraft:diamond_axe"),
    STICK("minecraft:stick"),
    BOWL("minecraft:bowl"),
    MUSHROOM_STEW("minecraft:mushroom_stew"),
    GOLDEN_SWORD("minecraft:golden_sword"),
    GOLDEN_SHOVEL("minecraft:golden_shovel"),
    GOLDEN_PICKAXE("minecraft:golden_pickaxe"),
    GOLDEN_AXE("minecraft:golden_axe"),
    STRING("minecraft:string"),
    FEATHER("minecraft:feather"),
    GUNPOWDER("minecraft:gunpowder"),
    WOODEN_HOE("minecraft:wooden_hoe"),
    STONE_HOE("minecraft:stone_hoe"),
    IRON_HOE("minecraft:iron_hoe"),
    DIAMOND_HOE("minecraft:diamond_hoe"),
    GOLDEN_HOE("minecraft:golden_hoe"),
    WHEAT_SEEDS("minecraft:wheat_seeds"),
    WHEAT("minecraft:wheat"),
    BREAD("minecraft:bread"),
    LEATHER_HELMET("minecraft:leather_helmet"),
    LEATHER_CHESTPLATE("minecraft:leather_chestplate"),
    LEATHER_LEGGINGS("minecraft:leather_leggings"),
    LEATHER_BOOTS("minecraft:leather_boots"),
    CHAINMAIL_HELMET("minecraft:chainmail_helmet"),
    CHAINMAIL_CHESTPLATE("minecraft:chainmail_chestplate"),
    CHAINMAIL_LEGGINGS("minecraft:chainmail_leggings"),
    CHAINMAIL_BOOTS("minecraft:chainmail_boots"),
    IRON_HELMET("minecraft:iron_helmet"),
    IRON_CHESTPLATE("minecraft:iron_chestplate"),
    IRON_LEGGINGS("minecraft:iron_leggings"),
    IRON_BOOTS("minecraft:iron_boots"),
    DIAMOND_HELMET("minecraft:diamond_helmet"),
    DIAMOND_CHESTPLATE("minecraft:diamond_chestplate"),
    DIAMOND_LEGGINGS("minecraft:diamond_leggings"),
    DIAMOND_BOOTS("minecraft:diamond_boots"),
    GOLDEN_HELMET("minecraft:golden_helmet"),
    GOLDEN_CHESTPLATE("minecraft:golden_chestplate"),
    GOLDEN_LEGGINGS("minecraft:golden_leggings"),
    GOLDEN_BOOTS("minecraft:golden_boots"),
    FLINT("minecraft:flint"),
    PORKCHOP("minecraft:porkchop"),
    COOKED_PORKCHOP("minecraft:cooked_porkchop"),
    PAINTING("minecraft:painting"),
    GOLDEN_APPLE("minecraft:golden_apple"),
    ENCHANTED_GOLDEN_APPLE("minecraft:enchanted_golden_apple"),
    SIGN("minecraft:sign"),
    OAK_DOOR("minecraft:oak_door"),
    BUCKET("minecraft:bucket"),
    WATER_BUCKET("minecraft:water_bucket"),
    LAVA_BUCKET("minecraft:lava_bucket"),
    MINECART("minecraft:minecart"),
    SADDLE("minecraft:saddle"),
    IRON_DOOR("minecraft:iron_door"),
    REDSTONE("minecraft:redstone"),
    SNOWBALL("minecraft:snowball"),
    OAK_BOAT("minecraft:oak_boat"),
    LEATHER("minecraft:leather"),
    MILK_BUCKET("minecraft:milk_bucket"),
    BRICK("minecraft:brick"),
    CLAY_BALL("minecraft:clay_ball"),
    SUGAR_CANE("minecraft:sugar_cane"),
    PAPER("minecraft:paper"),
    BOOK("minecraft:book"),
    SLIME_BALL("minecraft:slime_ball"),
    CHEST_MINECART("minecraft:chest_minecart"),
    FURNACE_MINECART("minecraft:furnace_minecart"),
    EGG("minecraft:egg"),
    TURTLE_EGG("minecraft:turtle_egg"),
    TWO_TURTLE_EGGS("minecraft:two_turtle_eggs"),
    THREE_TURTLE_EGGS("minecraft:three_turtle_eggs"),
    FOUR_TURTLE_EGGS("minecraft:four_turtle_eggs"),
    COMPASS("minecraft:compass"),
    FISHING_ROD("minecraft:fishing_rod"),
    CLOCK("minecraft:clock"),
    GLOWSTONE_DUST("minecraft:glowstone_dust"),
    COD("minecraft:cod"),
    SALMON("minecraft:salmon"),
    PUFFERFISH_BUCKET("minecraft:pufferfish_bucket"),
    SALMON_BUCKET("minecraft:salmon_bucket"),
    COD_BUCKET("minecraft:cod_bucket"),
    TROPICAL_FISH_BUCKET("minecraft:tropical_fish_bucket"),
    TROPICAL_FISH("minecraft:tropical_fish"),
    PUFFERFISH("minecraft:pufferfish"),
    COOKED_COD("minecraft:cooked_cod"),
    COOKED_SALMON("minecraft:cooked_salmon"),
    INK_SAC("minecraft:ink_sac"),
    ROSE_RED("minecraft:rose_red"),
    CACTUS_GREEN("minecraft:cactus_green"),
    COCOA_BEANS("minecraft:cocoa_beans"),
    LAPIS_LAZULI("minecraft:lapis_lazuli"),
    PURPLE_DYE("minecraft:purple_dye"),
    CYAN_DYE("minecraft:cyan_dye"),
    LIGHT_GRAY_DYE("minecraft:light_gray_dye"),
    GRAY_DYE("minecraft:gray_dye"),
    PINK_DYE("minecraft:pink_dye"),
    LIME_DYE("minecraft:lime_dye"),
    DANDELION_YELLOW("minecraft:dandelion_yellow"),
    LIGHT_BLUE_DYE("minecraft:light_blue_dye"),
    MAGENTA_DYE("minecraft:magenta_dye"),
    ORANGE_DYE("minecraft:orange_dye"),
    BONE_MEAL("minecraft:bone_meal"),
    BONE("minecraft:bone"),
    SUGAR("minecraft:sugar"),
    CAKE("minecraft:cake"),
    COOKIE("minecraft:cookie"),
    FILLED_MAP("minecraft:filled_map"),
    SHEARS("minecraft:shears"),
    MELON_SLICE("minecraft:melon_slice"),
    PUMPKIN_SEEDS("minecraft:pumpkin_seeds"),
    MELON_SEEDS("minecraft:melon_seeds"),
    BEEF("minecraft:beef"),
    COOKED_BEEF("minecraft:cooked_beef"),
    CHICKEN("minecraft:chicken"),
    COOKED_CHICKEN("minecraft:cooked_chicken"),
    ROTTEN_FLESH("minecraft:rotten_flesh"),
    ENDER_PEARL("minecraft:ender_pearl"),
    BLAZE_ROD("minecraft:blaze_rod"),
    GHAST_TEAR("minecraft:ghast_tear"),
    GOLD_NUGGET("minecraft:gold_nugget"),
    NETHER_WART("minecraft:nether_wart"),
    POTION("minecraft:potion"),
    GLASS_BOTTLE("minecraft:glass_bottle"),
    SPIDER_EYE("minecraft:spider_eye"),
    FERMENTED_SPIDER_EYE("minecraft:fermented_spider_eye"),
    BLAZE_POWDER("minecraft:blaze_powder"),
    MAGMA_CREAM("minecraft:magma_cream"),
    ENDER_EYE("minecraft:ender_eye"),
    END_CRYSTAL("minecraft:end_crystal"),
    GLISTERING_MELON_SLICE("minecraft:glistering_melon_slice"),
    EXPERIENCE_BOTTLE("minecraft:experience_bottle"),
    FIRE_CHARGE("minecraft:fire_charge"),
    WRITABLE_BOOK("minecraft:writable_book"),
    WRITTEN_BOOK("minecraft:written_book"),
    EMERALD("minecraft:emerald"),
    ITEM_FRAME("minecraft:item_frame"),
    FLOWER_POT("minecraft:flower_pot"),
    CARROT("minecraft:carrot"),
    POTATO("minecraft:potato"),
    SEA_PICKLE("minecraft:sea_pickle"),
    BAKED_POTATO("minecraft:baked_potato"),
    POISONOUS_POTATO("minecraft:poisonous_potato"),
    MAP("minecraft:map"),
    GOLDEN_CARROT("minecraft:golden_carrot"),
    WITHER_SKELETON_SKULL("minecraft:wither_skeleton_skull"),
    DRAGON_HEAD("minecraft:dragon_head"),
    CARROT_ON_A_STICK("minecraft:carrot_on_a_stick"),
    NETHER_STAR("minecraft:nether_star"),
    PUMPKIN_PIE("minecraft:pumpkin_pie"),
    FIREWORK_ROCKET("minecraft:firework_rocket"),
    FIREWORK_STAR("minecraft:firework_star"),
    ENCHANTED_BOOK("minecraft:enchanted_book"),
    NETHER_BRICK("minecraft:nether_brick"),
    QUARTZ("minecraft:quartz"),
    TNT_MINECART("minecraft:tnt_minecart"),
    HOPPER_MINECART("minecraft:hopper_minecart"),
    PRISMARINE_SHARD("minecraft:prismarine_shard"),
    PRISMARINE_CRYSTALS("minecraft:prismarine_crystals"),
    RABBIT("minecraft:rabbit"),
    COOKED_RABBIT("minecraft:cooked_rabbit"),
    RABBIT_STEW("minecraft:rabbit_stew"),
    RABBIT_FOOT("minecraft:rabbit_foot"),
    RABBIT_HIDE("minecraft:rabbit_hide"),
    ARMOR_STAND("minecraft:armor_stand"),
    IRON_HORSE_ARMOR("minecraft:iron_horse_armor"),
    GOLDEN_HORSE_ARMOR("minecraft:golden_horse_armor"),
    DIAMOND_HORSE_ARMOR("minecraft:diamond_horse_armor"),
    LEAD("minecraft:lead"),
    NAME_TAG("minecraft:name_tag"),
    MUTTON("minecraft:mutton"),
    COOKED_MUTTON("minecraft:cooked_mutton"),
    SPRUCE_DOOR("minecraft:spruce_door"),
    BIRCH_DOOR("minecraft:birch_door"),
    CHORUS_FRUIT("minecraft:chorus_fruit"),
    POPPED_CHORUS_FRUIT("minecraft:popped_chorus_fruit"),
    BEETROOT("minecraft:beetroot"),
    BEETROOT_SEEDS("minecraft:beetroot_seeds"),
    BEETROOT_SOUP("minecraft:beetroot_soup"),
    DRAGON_BREATH("minecraft:dragon_breath"),
    SPLASH_POTION("minecraft:splash_potion"),
    SPECTRAL_ARROW("minecraft:spectral_arrow"),
    TIPPED_ARROW("minecraft:tipped_arrow"),
    LINGERING_POTION("minecraft:lingering_potion"),
    SHIELD("minecraft:shield"),
    ELYTRA("minecraft:elytra"),
    SPRUCE_BOAT("minecraft:spruce_boat"),
    BIRCH_BOAT("minecraft:birch_boat"),
    JUNGLE_BOAT("minecraft:jungle_boat"),
    ACACIA_BOAT("minecraft:acacia_boat"),
    DARK_OAK_BOAT("minecraft:dark_oak_boat"),
    TOTEM_OF_UNDYING("minecraft:totem_of_undying"),
    SHULKER_SHELL("minecraft:shulker_shell"),
    IRON_NUGGET("minecraft:iron_nugget"),
    KELP("minecraft:kelp"),
    DRIED_KELP("minecraft:dried_kelp"),
    MUSIC_DISC_13("minecraft:music_disc_13"),
    MUSIC_DISC_CAT("minecraft:music_disc_cat"),
    MUSIC_DISC_BLOCKS("minecraft:music_disc_blocks"),
    MUSIC_DISC_CHIRP("minecraft:music_disc_chirp"),
    MUSIC_DISC_FAR("minecraft:music_disc_far"),
    MUSIC_DISC_MALL("minecraft:music_disc_mall"),
    MUSIC_DISC_MELLOHI("minecraft:music_disc_mellohi"),
    MUSIC_DISC_STAL("minecraft:music_disc_stal"),
    MUSIC_DISC_STRAD("minecraft:music_disc_strad"),
    MUSIC_DISC_WARD("minecraft:music_disc_ward"),
    MUSIC_DISC_11("minecraft:music_disc_11"),
    MUSIC_DISC_WAIT("minecraft:music_disc_wait"),
    MUSIC_DISC_PIGSTEP("minecraft:music_disc_pigstep"),
    KNOWLEDGE_BOOK("minecraft:knowledge_book"),
    TRIDENT("minecraft:trident"),
    SCUTE("minecraft:scute"),
    TURTLE_HELMET("minecraft:turtle_helmet"),
    PHANTOM_MEMBRANE("minecraft:phantom_membrane"),
    NAUTILUS_SHELL("minecraft:nautilus_shell"),
    HEART_OF_THE_SEA("minecraft:heart_of_the_sea"),
    BAMBOO("minecraft:bamboo"),
    SWEET_BERRIES("minecraft:sweet_berries"),
    CORNFLOWER("minecraft:cornflower"),
    LILY_OF_THE_VALLEY("minecraft:lily_of_the_valley"),
    WITHER_ROSE("minecraft:wither_rose"),
    OAK_SIGN("minecraft:oak_sign"),
    SPRUCE_SIGN("minecraft:spruce_sign"),
    BIRCH_SIGN("minecraft:birch_sign"),
    JUNGLE_SIGN("minecraft:jungle_sign"),
    ACACIA_SIGN("minecraft:acacia_sign"),
    DARK_OAK_SIGN("minecraft:dark_oak_sign"),
    SUSPICIOUS_STEW("minecraft:suspicious_stew"),
    CROSSBOW("minecraft:crossbow"),
    HONEY_BOTTLE("minecraft:honey_bottle"),
    HONEYCOMB("minecraft:honeycomb"),
    TWISTING_VINES("minecraft:twisting_vines"),
    WEEPING_VINES("minecraft:weeping_vines"),
    WARPED_FUNGUS_ON_A_STICK("minecraft:warped_fungus_on_a_stick"),
    CRIMSON_SIGN("minecraft:crimson_sign"),
    WARPED_SIGN("minecraft:warped_sign"),
    FLOWER_BANNER_PATTERN("minecraft:flower_banner_pattern"),
    CREEPER_BANNER_PATTERN("minecraft:creeper_banner_pattern"),
    SKULL_BANNER_PATTERN("minecraft:skull_banner_pattern"),
    MOJANG_BANNER_PATTERN("minecraft:mojang_banner_pattern"),
    GLOBE_BANNER_PATTERN("minecraft:globe_banner_pattern"),
    PIGLIN_BANNER_PATTERN("minecraft:piglin_banner_pattern"),
    NETHERITE_INGOT("minecraft:netherite_ingot"),
    NETHERITE_SCRAP("minecraft:netherite_scrap"),
    NETHERITE_HELMET("minecraft:netherite_helmet"),
    NETHERITE_CHESTPLATE("minecraft:netherite_chestplate"),
    NETHERITE_LEGGINGS("minecraft:netherite_leggings"),
    NETHERITE_BOOTS("minecraft:netherite_boots"),
    NETHERITE_SWORD("minecraft:netherite_sword"),
    NETHERITE_PICKAXE("minecraft:netherite_pickaxe"),
    NETHERITE_AXE("minecraft:netherite_axe"),
    NETHERITE_SHOVEL("minecraft:netherite_shovel"),
    NETHERITE_HOE("minecraft:netherite_hoe"),
    NETHER_SPROUTS("minecraft:nether_sprouts"),
    AMETHYST_SHARD("minecraft:amethyst_shard"),
    BUNDLE("minecraft:bundle"),
    COPPER_INGOT("minecraft:copper_ingot"),
    GLOW_BERRIES("minecraft:glow_berries"),
    GLOW_INK_SAC("minecraft:glow_ink_sac"),
    POWDER_SNOW_BUCKET("minecraft:powder_snow_bucket"),
    RAW_IRON("minecraft:raw_iron"),
    SPYGLASS("minecraft:spyglass");

    companion object {
        fun valueOfOrNull(name: String): ItemKeys? {
            for (itemKey in values()) {
                if (itemKey.name == name) {
                    return itemKey
                }
            }

            return null
        }
    }
}