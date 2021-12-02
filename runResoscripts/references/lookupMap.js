const lookupMap = {
  AccessibilityFeatures: [
    {
      lookupValue: "AccessibleApproachWithRamp",
      lookupDisplayName: "Accessible Approach with Ramp",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Accessible+Approach+with+Ramp",
    },
    {
      lookupValue: "AccessibleBedroom",
      lookupDisplayName: "Accessible Bedroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accessible+Bedroom",
    },
    {
      lookupValue: "AccessibleCentralLivingArea",
      lookupDisplayName: "Accessible Central Living Area",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Accessible+Central+Living+Area",
    },
    {
      lookupValue: "AccessibleClosets",
      lookupDisplayName: "Accessible Closets",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accessible+Closets",
    },
    {
      lookupValue: "AccessibleCommonArea",
      lookupDisplayName: "Accessible Common Area",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Accessible+Common+Area",
    },
    {
      lookupValue: "AccessibleDoors",
      lookupDisplayName: "Accessible Doors",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accessible+Doors",
    },
    {
      lookupValue: "AccessibleElectricalAndEnvironmentalControls",
      lookupDisplayName: "Accessible Electrical and Environmental Controls",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Accessible+Electrical+and+Environmental+Controls",
    },
    {
      lookupValue: "AccessibleElevatorInstalled",
      lookupDisplayName: "Accessible Elevator Installed",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Accessible+Elevator+Installed",
    },
    {
      lookupValue: "AccessibleEntrance",
      lookupDisplayName: "Accessible Entrance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accessible+Entrance",
    },
    {
      lookupValue: "AccessibleForHearingImpairment",
      lookupDisplayName: "Accessible for Hearing-Impairment",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Accessible+for+Hearing-Impairment",
    },
    {
      lookupValue: "AccessibleFullBath",
      lookupDisplayName: "Accessible Full Bath",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accessible+Full+Bath",
    },
    {
      lookupValue: "AccessibleHallways",
      lookupDisplayName: "Accessible Hallway(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243658",
    },
    {
      lookupValue: "AccessibleKitchen",
      lookupDisplayName: "Accessible Kitchen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accessible+Kitchen",
    },
    {
      lookupValue: "AccessibleKitchenAppliances",
      lookupDisplayName: "Accessible Kitchen Appliances",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Accessible+Kitchen+Appliances",
    },
    {
      lookupValue: "AccessibleStairway",
      lookupDisplayName: "Accessible Stairway",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accessible+Stairway",
    },
    {
      lookupValue: "AccessibleWasherDryer",
      lookupDisplayName: "Accessible Washer/Dryer",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243662",
    },
    {
      lookupValue: "AdaptableBathroomWalls",
      lookupDisplayName: "Adaptable Bathroom Walls",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Adaptable+Bathroom+Walls",
    },
    {
      lookupValue: "AdaptableForElevator",
      lookupDisplayName: "Adaptable For Elevator",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Adaptable+For+Elevator",
    },
    {
      lookupValue: "CeilingTrack",
      lookupDisplayName: "Ceiling Track",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ceiling+Track",
    },
    {
      lookupValue: "CentralLivingArea",
      lookupDisplayName: "Central Living Area",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Central+Living+Area",
    },
    {
      lookupValue: "CommonArea",
      lookupDisplayName: "Common Area",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Common+Area",
    },
    {
      lookupValue: "CustomizedWheelchairAccessible",
      lookupDisplayName: "Customized Wheelchair Accessible",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Customized+Wheelchair+Accessible",
    },
    {
      lookupValue: "ElectronicEnvironmentalControls",
      lookupDisplayName: "Electronic Environmental Controls",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Electronic+Environmental+Controls",
    },
    {
      lookupValue: "EnhancedAccessible",
      lookupDisplayName: "Enhanced Accessible",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Enhanced+Accessible",
    },
    {
      lookupValue: "ExteriorWheelchairLift",
      lookupDisplayName: "Exterior Wheelchair Lift",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Exterior+Wheelchair+Lift",
    },
    {
      lookupValue: "GripAccessibleFeatures",
      lookupDisplayName: "Grip-Accessible Features",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Grip-Accessible+Features",
    },
    {
      lookupValue: "ReinforcedFloors",
      lookupDisplayName: "Reinforced Floors",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Reinforced+Floors",
    },
    {
      lookupValue: "SafeEmergencyEgressFromHome",
      lookupDisplayName: "Safe Emergency Egress from Home",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Safe+Emergency+Egress+from+Home",
    },
    {
      lookupValue: "SmartTechnology",
      lookupDisplayName: "Smart Technology",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Smart+Technology",
    },
    {
      lookupValue: "StairLift",
      lookupDisplayName: "Stair Lift",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stair+Lift",
    },
    {
      lookupValue: "StandbyGenerator",
      lookupDisplayName: "Standby Generator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Standby+Generator",
    },
    {
      lookupValue: "TherapeuticWhirlpool",
      lookupDisplayName: "Therapeutic Whirlpool",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Therapeutic+Whirlpool",
    },
    {
      lookupValue: "Visitable",
      lookupDisplayName: "Visitable",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Visitable",
    },
    {
      lookupValue: "VisitorBathroom",
      lookupDisplayName: "Visitor Bathroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Visitor+Bathroom",
    },
    {
      lookupValue: "WalkerAccessibleStairs",
      lookupDisplayName: "Walker-Accessible Stairs",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Walker-Accessible+Stairs",
    },
  ],
  ActorType: [
    {
      lookupValue: "Agent",
      lookupDisplayName: "Agent",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243683",
    },
    {
      lookupValue: "Bot",
      lookupDisplayName: "Bot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bot",
    },
    {
      lookupValue: "Client",
      lookupDisplayName: "Client",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Client",
    },
    {
      lookupValue: "Consumer",
      lookupDisplayName: "Consumer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Consumer",
    },
    {
      lookupValue: "Unknown",
      lookupDisplayName: "Unknown",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243687",
    },
  ],
  Appliances: [
    {
      lookupValue: "BarFridge",
      lookupDisplayName: "Bar Fridge",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bar+Fridge",
    },
    {
      lookupValue: "BuiltInElectricOven",
      lookupDisplayName: "Built-In Electric Oven",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Built-In+Electric+Oven",
    },
    {
      lookupValue: "BuiltInElectricRange",
      lookupDisplayName: "Built-In Electric Range",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Built-In+Electric+Range",
    },
    {
      lookupValue: "BuiltInFreezer",
      lookupDisplayName: "Built-In Freezer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Built-In+Freezer",
    },
    {
      lookupValue: "BuiltInGasOven",
      lookupDisplayName: "Built-In Gas Oven",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Built-In+Gas+Oven",
    },
    {
      lookupValue: "BuiltInGasRange",
      lookupDisplayName: "Built-In Gas Range",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Built-In+Gas+Range",
    },
    {
      lookupValue: "BuiltInRange",
      lookupDisplayName: "Built-In Range",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Built-In+Range",
    },
    {
      lookupValue: "BuiltInRefrigerator",
      lookupDisplayName: "Built-In Refrigerator",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Built-In+Refrigerator",
    },
    {
      lookupValue: "ConvectionOven",
      lookupDisplayName: "Convection Oven",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Convection+Oven",
    },
    {
      lookupValue: "Cooktop",
      lookupDisplayName: "Cooktop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cooktop",
    },
    {
      lookupValue: "Dishwasher",
      lookupDisplayName: "Dishwasher",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dishwasher",
    },
    {
      lookupValue: "Disposal",
      lookupDisplayName: "Disposal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Disposal",
    },
    {
      lookupValue: "DoubleOven",
      lookupDisplayName: "Double Oven",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Double+Oven",
    },
    {
      lookupValue: "DownDraft",
      lookupDisplayName: "Down Draft",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Down+Draft",
    },
    {
      lookupValue: "Dryer",
      lookupDisplayName: "Dryer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dryer",
    },
    {
      lookupValue: "ElectricCooktop",
      lookupDisplayName: "Electric Cooktop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electric+Cooktop",
    },
    {
      lookupValue: "ElectricOven",
      lookupDisplayName: "Electric Oven",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electric+Oven",
    },
    {
      lookupValue: "ElectricRange",
      lookupDisplayName: "Electric Range",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electric+Range",
    },
    {
      lookupValue: "ElectricWaterHeater",
      lookupDisplayName: "Electric Water Heater",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Electric+Water+Heater",
    },
    {
      lookupValue: "EnergyStarQualifiedAppliances",
      lookupDisplayName: "ENERGY STAR Qualified Appliances",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Appliances",
    },
    {
      lookupValue: "EnergyStarQualifiedDishwasher",
      lookupDisplayName: "ENERGY STAR Qualified Dishwasher",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Dishwasher",
    },
    {
      lookupValue: "EnergyStarQualifiedDryer",
      lookupDisplayName: "ENERGY STAR Qualified Dryer",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Dryer",
    },
    {
      lookupValue: "EnergyStarQualifiedFreezer",
      lookupDisplayName: "ENERGY STAR Qualified Freezer",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Freezer",
    },
    {
      lookupValue: "EnergyStarQualifiedRefrigerator",
      lookupDisplayName: "ENERGY STAR Qualified Refrigerator",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Refrigerator",
    },
    {
      lookupValue: "EnergyStarQualifiedWasher",
      lookupDisplayName: "ENERGY STAR Qualified Washer",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Washer",
    },
    {
      lookupValue: "EnergyStarQualifiedWaterHeater",
      lookupDisplayName: "ENERGY STAR Qualified Water Heater",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Water+Heater",
    },
    {
      lookupValue: "ExhaustFan",
      lookupDisplayName: "Exhaust Fan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Exhaust+Fan",
    },
    {
      lookupValue: "FreeStandingElectricOven",
      lookupDisplayName: "Free-Standing Electric Oven",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Free-Standing+Electric+Oven",
    },
    {
      lookupValue: "FreeStandingElectricRange",
      lookupDisplayName: "Free-Standing Electric Range",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Free-Standing+Electric+Range",
    },
    {
      lookupValue: "FreeStandingFreezer",
      lookupDisplayName: "Free-Standing Freezer",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Free-Standing+Freezer",
    },
    {
      lookupValue: "FreeStandingGasOven",
      lookupDisplayName: "Free-Standing Gas Oven",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Free-Standing+Gas+Oven",
    },
    {
      lookupValue: "FreeStandingGasRange",
      lookupDisplayName: "Free-Standing Gas Range",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Free-Standing+Gas+Range",
    },
    {
      lookupValue: "FreeStandingRange",
      lookupDisplayName: "Free-Standing Range",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Free-Standing+Range",
    },
    {
      lookupValue: "FreeStandingRefrigerator",
      lookupDisplayName: "Free-Standing Refrigerator",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Free-Standing+Refrigerator",
    },
    {
      lookupValue: "Freezer",
      lookupDisplayName: "Freezer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Freezer",
    },
    {
      lookupValue: "GasCooktop",
      lookupDisplayName: "Gas Cooktop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Cooktop",
    },
    {
      lookupValue: "GasOven",
      lookupDisplayName: "Gas Oven",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Oven",
    },
    {
      lookupValue: "GasRange",
      lookupDisplayName: "Gas Range",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Range",
    },
    {
      lookupValue: "GasWaterHeater",
      lookupDisplayName: "Gas Water Heater",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Water+Heater",
    },
    {
      lookupValue: "Humidifier",
      lookupDisplayName: "Humidifier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Humidifier",
    },
    {
      lookupValue: "IceMaker",
      lookupDisplayName: "Ice Maker",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ice+Maker",
    },
    {
      lookupValue: "IndoorGrill",
      lookupDisplayName: "Indoor Grill",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Indoor+Grill",
    },
    {
      lookupValue: "InductionCooktop",
      lookupDisplayName: "Induction Cooktop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Induction+Cooktop",
    },
    {
      lookupValue: "InstantHotWater",
      lookupDisplayName: "Instant Hot Water",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Instant+Hot+Water",
    },
    {
      lookupValue: "Microwave",
      lookupDisplayName: "Microwave",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Microwave",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243734",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243735",
    },
    {
      lookupValue: "Oven",
      lookupDisplayName: "Oven",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Oven",
    },
    {
      lookupValue: "PlumbedForIceMaker",
      lookupDisplayName: "Plumbed For Ice Maker",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Plumbed+For+Ice+Maker",
    },
    {
      lookupValue: "PortableDishwasher",
      lookupDisplayName: "Portable Dishwasher",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Portable+Dishwasher",
    },
    {
      lookupValue: "PropaneCooktop",
      lookupDisplayName: "Propane Cooktop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Propane+Cooktop",
    },
    {
      lookupValue: "Range",
      lookupDisplayName: "Range",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Range",
    },
    {
      lookupValue: "RangeHood",
      lookupDisplayName: "Range Hood",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Range+Hood",
    },
    {
      lookupValue: "Refrigerator",
      lookupDisplayName: "Refrigerator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Refrigerator",
    },
    {
      lookupValue: "SelfCleaningOven",
      lookupDisplayName: "Self Cleaning Oven",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Self+Cleaning+Oven",
    },
    {
      lookupValue: "SolarHotWater",
      lookupDisplayName: "Solar Hot Water",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Solar+Hot+Water",
    },
    {
      lookupValue: "StainlessSteelAppliances",
      lookupDisplayName: "Stainless Steel Appliance(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243745",
    },
    {
      lookupValue: "TanklessWaterHeater",
      lookupDisplayName: "Tankless Water Heater",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Tankless+Water+Heater",
    },
    {
      lookupValue: "TrashCompactor",
      lookupDisplayName: "Trash Compactor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Trash+Compactor",
    },
    {
      lookupValue: "VentedExhaustFan",
      lookupDisplayName: "Vented Exhaust Fan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vented+Exhaust+Fan",
    },
    {
      lookupValue: "WarmingDrawer",
      lookupDisplayName: "Warming Drawer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Warming+Drawer",
    },
    {
      lookupValue: "Washer",
      lookupDisplayName: "Washer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Washer",
    },
    {
      lookupValue: "WasherDryer",
      lookupDisplayName: "Washer/Dryer",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243751",
    },
    {
      lookupValue: "WasherDryerStacked",
      lookupDisplayName: "Washer/Dryer Stacked",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243752",
    },
    {
      lookupValue: "WaterHeater",
      lookupDisplayName: "Water Heater",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243753",
    },
    {
      lookupValue: "WaterPurifier",
      lookupDisplayName: "Water Purifier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Purifier",
    },
    {
      lookupValue: "WaterPurifierOwned",
      lookupDisplayName: "Water Purifier Owned",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Purifier+Owned",
    },
    {
      lookupValue: "WaterPurifierRented",
      lookupDisplayName: "Water Purifier Rented",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Water+Purifier+Rented",
    },
    {
      lookupValue: "WaterSoftener",
      lookupDisplayName: "Water Softener",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Softener",
    },
    {
      lookupValue: "WaterSoftenerOwned",
      lookupDisplayName: "Water Softener Owned",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Softener+Owned",
    },
    {
      lookupValue: "WaterSoftenerRented",
      lookupDisplayName: "Water Softener Rented",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Water+Softener+Rented",
    },
    {
      lookupValue: "WineCooler",
      lookupDisplayName: "Wine Cooler",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wine+Cooler",
    },
    {
      lookupValue: "WineRefrigerator",
      lookupDisplayName: "Wine Refrigerator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wine+Refrigerator",
    },
  ],
  AreaSource: [
    {
      lookupValue: "Appraiser",
      lookupDisplayName: "Appraiser",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Appraiser",
    },
    {
      lookupValue: "Assessor",
      lookupDisplayName: "Assessor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Assessor",
    },
    {
      lookupValue: "Builder",
      lookupDisplayName: "Builder",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Builder",
    },
    {
      lookupValue: "Estimated",
      lookupDisplayName: "Estimated",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Estimated",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Other",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Owner",
    },
    {
      lookupValue: "Plans",
      lookupDisplayName: "Plans",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Plans",
    },
    {
      lookupValue: "PublicRecords",
      lookupDisplayName: "Public Records",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Public+Records",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/See+Remarks",
    },
  ],
  AreaUnits: [
    {
      lookupValue: "SquareFeet",
      lookupDisplayName: "Square Feet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Square+Feet",
    },
    {
      lookupValue: "SquareMeters",
      lookupDisplayName: "Square Meters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Square+Meters",
    },
  ],
  AssociationAmenities: [
    {
      lookupValue: "AirportRunway",
      lookupDisplayName: "Airport/Runway",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243763",
    },
    {
      lookupValue: "Barbecue",
      lookupDisplayName: "Barbecue",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Barbecue",
    },
    {
      lookupValue: "BasketballCourt",
      lookupDisplayName: "Basketball Court",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Basketball+Court",
    },
    {
      lookupValue: "BeachAccess",
      lookupDisplayName: "Beach Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Beach+Access",
    },
    {
      lookupValue: "BeachRights",
      lookupDisplayName: "Beach Rights",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Beach+Rights",
    },
    {
      lookupValue: "BilliardRoom",
      lookupDisplayName: "Billiard Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Billiard+Room",
    },
    {
      lookupValue: "BoatDock",
      lookupDisplayName: "Boat Dock",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Boat+Dock",
    },
    {
      lookupValue: "Boating",
      lookupDisplayName: "Boating",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Boating",
    },
    {
      lookupValue: "BoatSlip",
      lookupDisplayName: "Boat Slip",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243771",
    },
    {
      lookupValue: "Cabana",
      lookupDisplayName: "Cabana",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243773",
    },
    {
      lookupValue: "CableTv",
      lookupDisplayName: "Cable TV",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243774",
    },
    {
      lookupValue: "CarWashArea",
      lookupDisplayName: "Car Wash Area",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Car+Wash+Area",
    },
    {
      lookupValue: "Clubhouse",
      lookupDisplayName: "Clubhouse",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Clubhouse",
    },
    {
      lookupValue: "CoinLaundry",
      lookupDisplayName: "Coin Laundry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Coin+Laundry",
    },
    {
      lookupValue: "Concierge",
      lookupDisplayName: "Concierge",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Concierge",
    },
    {
      lookupValue: "DayCare",
      lookupDisplayName: "Day Care",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Day+Care",
    },
    {
      lookupValue: "DogPark",
      lookupDisplayName: "Dog Park",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dog+Park",
    },
    {
      lookupValue: "DryDock",
      lookupDisplayName: "Dry Dock",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dry+Dock",
    },
    {
      lookupValue: "Electricity",
      lookupDisplayName: "Electricity",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243782",
    },
    {
      lookupValue: "Elevators",
      lookupDisplayName: "Elevator(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243783",
    },
    {
      lookupValue: "ExerciseCourse",
      lookupDisplayName: "Exercise Course",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Exercise+Course",
    },
    {
      lookupValue: "FitnessCenter",
      lookupDisplayName: "Fitness Center",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fitness+Center",
    },
    {
      lookupValue: "GameCourtExterior",
      lookupDisplayName: "Game Court Exterior",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Game+Court+Exterior",
    },
    {
      lookupValue: "GameCourtInterior",
      lookupDisplayName: "Game Court Interior",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Game+Court+Interior",
    },
    {
      lookupValue: "GameRoom",
      lookupDisplayName: "Game Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243788",
    },
    {
      lookupValue: "Gas",
      lookupDisplayName: "Gas",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243789",
    },
    {
      lookupValue: "Gated",
      lookupDisplayName: "Gated",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gated",
    },
    {
      lookupValue: "GolfCourse",
      lookupDisplayName: "Golf Course",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243791",
    },
    {
      lookupValue: "HotWater",
      lookupDisplayName: "Hot Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243792",
    },
    {
      lookupValue: "IndoorPool",
      lookupDisplayName: "Indoor Pool",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Indoor+Pool",
    },
    {
      lookupValue: "Insurance",
      lookupDisplayName: "Insurance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243794",
    },
    {
      lookupValue: "JoggingPath",
      lookupDisplayName: "Jogging Path",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Jogging+Path",
    },
    {
      lookupValue: "Landscaping",
      lookupDisplayName: "Landscaping",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243796",
    },
    {
      lookupValue: "Laundry",
      lookupDisplayName: "Laundry",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243797",
    },
    {
      lookupValue: "MaidService",
      lookupDisplayName: "Maid service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Maid+service",
    },
    {
      lookupValue: "Maintenance",
      lookupDisplayName: "Maintenance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243799",
    },
    {
      lookupValue: "MaintenanceGrounds",
      lookupDisplayName: "Maintenance Grounds",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243838",
    },
    {
      lookupValue: "MaintenanceStructure",
      lookupDisplayName: "Maintenance Structure",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243772",
    },
    {
      lookupValue: "Management",
      lookupDisplayName: "Management",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243800",
    },
    {
      lookupValue: "Marina",
      lookupDisplayName: "Marina",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Marina",
    },
    {
      lookupValue: "MeetingRoom",
      lookupDisplayName: "Meeting Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Meeting+Room",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243803",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243804",
    },
    {
      lookupValue: "Park",
      lookupDisplayName: "Park",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Park",
    },
    {
      lookupValue: "Parking",
      lookupDisplayName: "Parking",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243806",
    },
    {
      lookupValue: "PartyRoom",
      lookupDisplayName: "Party Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Party+Room",
    },
    {
      lookupValue: "PicnicArea",
      lookupDisplayName: "Picnic Area",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Picnic+Area",
    },
    {
      lookupValue: "Playground",
      lookupDisplayName: "Playground",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Playground",
    },
    {
      lookupValue: "PondSeasonal",
      lookupDisplayName: "Pond Seasonal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pond+Seasonal",
    },
    {
      lookupValue: "PondYearRound",
      lookupDisplayName: "Pond Year Round",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pond+Year+Round",
    },
    {
      lookupValue: "Pool",
      lookupDisplayName: "Pool",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243812",
    },
    {
      lookupValue: "PoweredBoatsAllowed",
      lookupDisplayName: "Powered Boats Allowed",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Powered+Boats+Allowed",
    },
    {
      lookupValue: "Racquetball",
      lookupDisplayName: "Racquetball",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Racquetball",
    },
    {
      lookupValue: "RecreationFacilities",
      lookupDisplayName: "Recreation Facilities",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Recreation+Facilities",
    },
    {
      lookupValue: "RecreationRoom",
      lookupDisplayName: "Recreation Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243816",
    },
    {
      lookupValue: "RoofDeck",
      lookupDisplayName: "Roof Deck",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Roof+Deck",
    },
    {
      lookupValue: "RvBoatStorage",
      lookupDisplayName: "RV/Boat Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243818",
    },
    {
      lookupValue: "RvParking",
      lookupDisplayName: "RV Parking",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RV+Parking",
    },
    {
      lookupValue: "Sauna",
      lookupDisplayName: "Sauna",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243820",
    },
    {
      lookupValue: "Security",
      lookupDisplayName: "Security",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243821",
    },
    {
      lookupValue: "ServiceElevators",
      lookupDisplayName: "Service Elevator(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243822",
    },
    {
      lookupValue: "ShuffleboardCourt",
      lookupDisplayName: "Shuffleboard Court",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shuffleboard+Court",
    },
    {
      lookupValue: "SkiAccessible",
      lookupDisplayName: "Ski Accessible",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ski+Accessible",
    },
    {
      lookupValue: "SnowRemoval",
      lookupDisplayName: "Snow Removal",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243825",
    },
    {
      lookupValue: "SpaHotTub",
      lookupDisplayName: "Spa/Hot Tub",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243826",
    },
    {
      lookupValue: "SportCourt",
      lookupDisplayName: "Sport Court",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sport+Court",
    },
    {
      lookupValue: "Stables",
      lookupDisplayName: "Stable(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243828",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243829",
    },
    {
      lookupValue: "StreamSeasonal",
      lookupDisplayName: "Stream Seasonal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stream+Seasonal",
    },
    {
      lookupValue: "StreamYearRound",
      lookupDisplayName: "Stream Year Round",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stream+Year+Round",
    },
    {
      lookupValue: "Taxes",
      lookupDisplayName: "Taxes",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243832",
    },
    {
      lookupValue: "TennisCourts",
      lookupDisplayName: "Tennis Court(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243833",
    },
    {
      lookupValue: "Trails",
      lookupDisplayName: "Trail(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243834",
    },
    {
      lookupValue: "Trash",
      lookupDisplayName: "Trash",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243835",
    },
    {
      lookupValue: "Water",
      lookupDisplayName: "Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243836",
    },
    {
      lookupValue: "WorkshopArea",
      lookupDisplayName: "Workshop Area",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Workshop+Area",
    },
  ],
  AssociationFeeIncludes: [
    {
      lookupValue: "CableTv",
      lookupDisplayName: "Cable TV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cable+TV",
    },
    {
      lookupValue: "EarthquakeInsurance",
      lookupDisplayName: "Earthquake Insurance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Earthquake+Insurance",
    },
    {
      lookupValue: "Electricity",
      lookupDisplayName: "Electricity",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electricity",
    },
    {
      lookupValue: "Gas",
      lookupDisplayName: "Gas",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas",
    },
    {
      lookupValue: "Insurance",
      lookupDisplayName: "Insurance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Insurance",
    },
    {
      lookupValue: "Internet",
      lookupDisplayName: "Internet",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243641",
    },
    {
      lookupValue: "MaintenanceGrounds",
      lookupDisplayName: "Maintenance Grounds",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Maintenance+Grounds",
    },
    {
      lookupValue: "MaintenanceStructure",
      lookupDisplayName: "Maintenance Structure",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243632",
    },
    {
      lookupValue: "PestControl",
      lookupDisplayName: "Pest Control",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pest+Control",
    },
    {
      lookupValue: "Security",
      lookupDisplayName: "Security",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Security",
    },
    {
      lookupValue: "Sewer",
      lookupDisplayName: "Sewer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sewer",
    },
    {
      lookupValue: "SnowRemoval",
      lookupDisplayName: "Snow Removal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Snow+Removal",
    },
    {
      lookupValue: "Trash",
      lookupDisplayName: "Trash",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Trash",
    },
    {
      lookupValue: "Utilities",
      lookupDisplayName: "Utilities",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Utilities",
    },
    {
      lookupValue: "Water",
      lookupDisplayName: "Water",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water",
    },
  ],
  Attended: [
    {
      lookupValue: "Agent",
      lookupDisplayName: "Agent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Agent",
    },
    {
      lookupValue: "Seller",
      lookupDisplayName: "Seller",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Seller",
    },
    {
      lookupValue: "Unattended",
      lookupDisplayName: "Unattended",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unattended",
    },
  ],
  Basement: [
    {
      lookupValue: "Apartment",
      lookupDisplayName: "Apartment",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244002",
    },
    {
      lookupValue: "BathStubbed",
      lookupDisplayName: "Bath/Stubbed",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244003",
    },
    {
      lookupValue: "Block",
      lookupDisplayName: "Block",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244004",
    },
    {
      lookupValue: "Concrete",
      lookupDisplayName: "Concrete",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244005",
    },
    {
      lookupValue: "CrawlSpace",
      lookupDisplayName: "Crawl Space",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Crawl+Space",
    },
    {
      lookupValue: "Daylight",
      lookupDisplayName: "Daylight",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Daylight",
    },
    {
      lookupValue: "DirtFloor",
      lookupDisplayName: "Dirt Floor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dirt+Floor",
    },
    {
      lookupValue: "ExteriorEntry",
      lookupDisplayName: "Exterior Entry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Exterior+Entry",
    },
    {
      lookupValue: "Finished",
      lookupDisplayName: "Finished",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Finished",
    },
    {
      lookupValue: "FrenchDrain",
      lookupDisplayName: "French Drain",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/French+Drain",
    },
    {
      lookupValue: "Full",
      lookupDisplayName: "Full",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244012",
    },
    {
      lookupValue: "InteriorEntry",
      lookupDisplayName: "Interior Entry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Interior+Entry",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244014",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244015",
    },
    {
      lookupValue: "Partial",
      lookupDisplayName: "Partial",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244016",
    },
    {
      lookupValue: "PartiallyFinished",
      lookupDisplayName: "Partially Finished",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Partially+Finished",
    },
    {
      lookupValue: "StorageSpace",
      lookupDisplayName: "Storage Space",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Storage+Space",
    },
    {
      lookupValue: "SumpPump",
      lookupDisplayName: "Sump Pump",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sump+Pump",
    },
    {
      lookupValue: "Unfinished",
      lookupDisplayName: "Unfinished",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unfinished",
    },
    {
      lookupValue: "WalkOutAccess",
      lookupDisplayName: "Walk-Out Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Walk-Out+Access",
    },
    {
      lookupValue: "WalkUpAccess",
      lookupDisplayName: "Walk-Up Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Walk-Up+Access",
    },
  ],
  BodyType: [
    {
      lookupValue: "DoubleWide",
      lookupDisplayName: "Double Wide",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Double+Wide",
    },
    {
      lookupValue: "Expando",
      lookupDisplayName: "Expando",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Expando",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243847",
    },
    {
      lookupValue: "QuadWide",
      lookupDisplayName: "Quad Wide",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Quad+Wide",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243844",
    },
    {
      lookupValue: "SingleWide",
      lookupDisplayName: "Single Wide",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Single+Wide",
    },
    {
      lookupValue: "TripleWide",
      lookupDisplayName: "Triple Wide",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Triple+Wide",
    },
  ],
  BusinessType: [
    {
      lookupValue: "Accounting",
      lookupDisplayName: "Accounting",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accounting",
    },
    {
      lookupValue: "AdministrativeAndSupport",
      lookupDisplayName: "Administrative and Support",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Administrative+and+Support",
    },
    {
      lookupValue: "Advertising",
      lookupDisplayName: "Advertising",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Advertising",
    },
    {
      lookupValue: "Agriculture",
      lookupDisplayName: "Agriculture",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Agriculture",
    },
    {
      lookupValue: "AnimalGrooming",
      lookupDisplayName: "Animal Grooming",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Animal+Grooming",
    },
    {
      lookupValue: "Appliances",
      lookupDisplayName: "Appliances",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Appliances",
    },
    {
      lookupValue: "AquariumSupplies",
      lookupDisplayName: "Aquarium Supplies",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aquarium+Supplies",
    },
    {
      lookupValue: "ArtsAndEntertainment",
      lookupDisplayName: "Arts and Entertainment",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Arts+and+Entertainment",
    },
    {
      lookupValue: "Athletic",
      lookupDisplayName: "Athletic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Athletic",
    },
    {
      lookupValue: "AutoBody",
      lookupDisplayName: "Auto Body",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auto+Body",
    },
    {
      lookupValue: "AutoDealer",
      lookupDisplayName: "Auto Dealer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auto+Dealer",
    },
    {
      lookupValue: "AutoGlass",
      lookupDisplayName: "Auto Glass",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auto+Glass",
    },
    {
      lookupValue: "AutoParts",
      lookupDisplayName: "Auto Parts",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auto+Parts",
    },
    {
      lookupValue: "AutoRentLease",
      lookupDisplayName: "Auto Rent/Lease",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243862",
    },
    {
      lookupValue: "AutoRepairSpecialty",
      lookupDisplayName: "Auto Repair-Specialty",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Auto+Repair-Specialty",
    },
    {
      lookupValue: "AutoService",
      lookupDisplayName: "Auto Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auto+Service",
    },
    {
      lookupValue: "AutoStereoAlarm",
      lookupDisplayName: "Auto Stereo/Alarm",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243865",
    },
    {
      lookupValue: "AutoTires",
      lookupDisplayName: "Auto Tires",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auto+Tires",
    },
    {
      lookupValue: "AutoWrecking",
      lookupDisplayName: "Auto Wrecking",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auto+Wrecking",
    },
    {
      lookupValue: "Bakery",
      lookupDisplayName: "Bakery",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bakery",
    },
    {
      lookupValue: "BarberBeauty",
      lookupDisplayName: "Barber/Beauty",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243870",
    },
    {
      lookupValue: "BarTavernLounge",
      lookupDisplayName: "Bar/Tavern/Lounge",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243869",
    },
    {
      lookupValue: "BedAndBreakfast",
      lookupDisplayName: "Bed & Breakfast",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243871",
    },
    {
      lookupValue: "BooksCardsStationary",
      lookupDisplayName: "Books/Cards/Stationary",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243872",
    },
    {
      lookupValue: "Butcher",
      lookupDisplayName: "Butcher",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Butcher",
    },
    {
      lookupValue: "Cabinets",
      lookupDisplayName: "Cabinets",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cabinets",
    },
    {
      lookupValue: "CandyCookie",
      lookupDisplayName: "Candy/Cookie",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243875",
    },
    {
      lookupValue: "CarpetTile",
      lookupDisplayName: "Carpet/Tile",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243877",
    },
    {
      lookupValue: "CarWash",
      lookupDisplayName: "Car Wash",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Car+Wash",
    },
    {
      lookupValue: "ChildCare",
      lookupDisplayName: "Child Care",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Child+Care",
    },
    {
      lookupValue: "Church",
      lookupDisplayName: "Church",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Church",
    },
    {
      lookupValue: "Clothing",
      lookupDisplayName: "Clothing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Clothing",
    },
    {
      lookupValue: "Commercial",
      lookupDisplayName: "Commercial",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Commercial",
    },
    {
      lookupValue: "Computer",
      lookupDisplayName: "Computer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Computer",
    },
    {
      lookupValue: "ConstructionContractor",
      lookupDisplayName: "Construction/Contractor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243883",
    },
    {
      lookupValue: "Convalescent",
      lookupDisplayName: "Convalescent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Convalescent",
    },
    {
      lookupValue: "ConvenienceStore",
      lookupDisplayName: "Convenience Store",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Convenience+Store",
    },
    {
      lookupValue: "DanceStudio",
      lookupDisplayName: "Dance Studio",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dance+Studio",
    },
    {
      lookupValue: "Decorator",
      lookupDisplayName: "Decorator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Decorator",
    },
    {
      lookupValue: "DeliCatering",
      lookupDisplayName: "Deli/Catering",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243888",
    },
    {
      lookupValue: "Dental",
      lookupDisplayName: "Dental",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dental",
    },
    {
      lookupValue: "Distribution",
      lookupDisplayName: "Distribution",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Distribution",
    },
    {
      lookupValue: "Doughnut",
      lookupDisplayName: "Doughnut",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Doughnut",
    },
    {
      lookupValue: "Drugstore",
      lookupDisplayName: "Drugstore",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Drugstore",
    },
    {
      lookupValue: "DryCleaner",
      lookupDisplayName: "Dry Cleaner",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dry+Cleaner",
    },
    {
      lookupValue: "EducationSchool",
      lookupDisplayName: "Education/School",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243894",
    },
    {
      lookupValue: "Electronics",
      lookupDisplayName: "Electronics",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electronics",
    },
    {
      lookupValue: "Employment",
      lookupDisplayName: "Employment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Employment",
    },
    {
      lookupValue: "Farm",
      lookupDisplayName: "Farm",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Farm",
    },
    {
      lookupValue: "FastFood",
      lookupDisplayName: "Fast Food",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fast+Food",
    },
    {
      lookupValue: "Financial",
      lookupDisplayName: "Financial",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Financial",
    },
    {
      lookupValue: "Fitness",
      lookupDisplayName: "Fitness",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fitness",
    },
    {
      lookupValue: "FloristNursery",
      lookupDisplayName: "Florist/Nursery",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243901",
    },
    {
      lookupValue: "FoodAndBeverage",
      lookupDisplayName: "Food & Beverage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243902",
    },
    {
      lookupValue: "ForestReserve",
      lookupDisplayName: "Forest Reserve",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Forest+Reserve",
    },
    {
      lookupValue: "Franchise",
      lookupDisplayName: "Franchise",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Franchise",
    },
    {
      lookupValue: "Furniture",
      lookupDisplayName: "Furniture",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Furniture",
    },
    {
      lookupValue: "GasStation",
      lookupDisplayName: "Gas Station",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Station",
    },
    {
      lookupValue: "GiftShop",
      lookupDisplayName: "Gift Shop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gift+Shop",
    },
    {
      lookupValue: "Grocery",
      lookupDisplayName: "Grocery",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Grocery",
    },
    {
      lookupValue: "Hardware",
      lookupDisplayName: "Hardware",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hardware",
    },
    {
      lookupValue: "HealthFood",
      lookupDisplayName: "Health Food",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Health+Food",
    },
    {
      lookupValue: "HealthServices",
      lookupDisplayName: "Health Services",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Health+Services",
    },
    {
      lookupValue: "Hobby",
      lookupDisplayName: "Hobby",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hobby",
    },
    {
      lookupValue: "HomeCleaner",
      lookupDisplayName: "Home Cleaner",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Home+Cleaner",
    },
    {
      lookupValue: "Hospitality",
      lookupDisplayName: "Hospitality",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hospitality",
    },
    {
      lookupValue: "HotelMotel",
      lookupDisplayName: "Hotel/Motel",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243915",
    },
    {
      lookupValue: "IceCreamFrozenYogurt",
      lookupDisplayName: "Ice Cream/Frozen Yogurt",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243916",
    },
    {
      lookupValue: "Industrial",
      lookupDisplayName: "Industrial",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Industrial",
    },
    {
      lookupValue: "Jewelry",
      lookupDisplayName: "Jewelry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Jewelry",
    },
    {
      lookupValue: "Landscaping",
      lookupDisplayName: "Landscaping",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Landscaping",
    },
    {
      lookupValue: "Laundromat",
      lookupDisplayName: "Laundromat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Laundromat",
    },
    {
      lookupValue: "LiquorStore",
      lookupDisplayName: "Liquor Store",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Liquor+Store",
    },
    {
      lookupValue: "Locksmith",
      lookupDisplayName: "Locksmith",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Locksmith",
    },
    {
      lookupValue: "Manufacturing",
      lookupDisplayName: "Manufacturing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manufacturing",
    },
    {
      lookupValue: "Medical",
      lookupDisplayName: "Medical",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Medical",
    },
    {
      lookupValue: "Mixed",
      lookupDisplayName: "Mixed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mixed",
    },
    {
      lookupValue: "MobileTrailerPark",
      lookupDisplayName: "Mobile/Trailer Park",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243927",
    },
    {
      lookupValue: "Music",
      lookupDisplayName: "Music",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Music",
    },
    {
      lookupValue: "NursingHome",
      lookupDisplayName: "Nursing Home",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Nursing+Home",
    },
    {
      lookupValue: "OfficeSupply",
      lookupDisplayName: "Office Supply",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Office+Supply",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243931",
    },
    {
      lookupValue: "Paints",
      lookupDisplayName: "Paints",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Paints",
    },
    {
      lookupValue: "Parking",
      lookupDisplayName: "Parking",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Parking",
    },
    {
      lookupValue: "PetStore",
      lookupDisplayName: "Pet Store",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pet+Store",
    },
    {
      lookupValue: "Photographer",
      lookupDisplayName: "Photographer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Photographer",
    },
    {
      lookupValue: "Pizza",
      lookupDisplayName: "Pizza",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pizza",
    },
    {
      lookupValue: "Printing",
      lookupDisplayName: "Printing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Printing",
    },
    {
      lookupValue: "ProfessionalOffice",
      lookupDisplayName: "Professional/Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243939",
    },
    {
      lookupValue: "ProfessionalService",
      lookupDisplayName: "Professional Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Professional+Service",
    },
    {
      lookupValue: "RealEstate",
      lookupDisplayName: "Real Estate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Real+Estate",
    },
    {
      lookupValue: "Recreation",
      lookupDisplayName: "Recreation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Recreation",
    },
    {
      lookupValue: "Rental",
      lookupDisplayName: "Rental",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rental",
    },
    {
      lookupValue: "Residential",
      lookupDisplayName: "Residential",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Residential",
    },
    {
      lookupValue: "Restaurant",
      lookupDisplayName: "Restaurant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Restaurant",
    },
    {
      lookupValue: "Retail",
      lookupDisplayName: "Retail",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Retail",
    },
    {
      lookupValue: "SaddleryHarness",
      lookupDisplayName: "Saddlery/Harness",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243946",
    },
    {
      lookupValue: "SportingGoods",
      lookupDisplayName: "Sporting Goods",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sporting+Goods",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Storage",
    },
    {
      lookupValue: "Toys",
      lookupDisplayName: "Toys",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Toys",
    },
    {
      lookupValue: "Transportation",
      lookupDisplayName: "Transportation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Transportation",
    },
    {
      lookupValue: "Travel",
      lookupDisplayName: "Travel",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Travel",
    },
    {
      lookupValue: "Upholstery",
      lookupDisplayName: "Upholstery",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Upholstery",
    },
    {
      lookupValue: "Utility",
      lookupDisplayName: "Utility",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Utility",
    },
    {
      lookupValue: "Variety",
      lookupDisplayName: "Variety",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Variety",
    },
    {
      lookupValue: "Video",
      lookupDisplayName: "Video",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Video",
    },
    {
      lookupValue: "Wallpaper",
      lookupDisplayName: "Wallpaper",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wallpaper",
    },
    {
      lookupValue: "Warehouse",
      lookupDisplayName: "Warehouse",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Warehouse",
    },
    {
      lookupValue: "Wholesale",
      lookupDisplayName: "Wholesale",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wholesale",
    },
  ],
  BuyerAgentDesignation: [
    {
      lookupValue: "AccreditedBuyersRepresentative",
      lookupDisplayName: "Accredited Buyer's Representative / ABR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243974",
    },
    {
      lookupValue: "AccreditedLandConsultant",
      lookupDisplayName: "Accredited Land Consultant / ALC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243975",
    },
    {
      lookupValue: "AtHomeWithDiversity",
      lookupDisplayName: "At Home With Diversity / AHWD",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243976",
    },
    {
      lookupValue: "CertifiedCommercialInvestmentMember",
      lookupDisplayName: "Certified Commercial Investment Member / CCIM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243977",
    },
    {
      lookupValue: "CertifiedDistressedPropertyExpert",
      lookupDisplayName: "Certified Distressed Property Expert / CDPE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243978",
    },
    {
      lookupValue: "CertifiedInternationalPropertySpecialist",
      lookupDisplayName: "Certified International Property Specialist / CIPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243979",
    },
    {
      lookupValue: "CertifiedPropertyManager",
      lookupDisplayName: "Certified Property Manager / CPM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243980",
    },
    {
      lookupValue: "CertifiedRealEstateBrokerageManager",
      lookupDisplayName: "Certified Real Estate Brokerage Manager / CRB",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243981",
    },
    {
      lookupValue: "CertifiedRealEstateTeamSpecialist",
      lookupDisplayName: "Certified Real Estate Team Specialist / C-RETS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243982",
    },
    {
      lookupValue: "CertifiedResidentialSpecialist",
      lookupDisplayName: "Certified Residential Specialist / CRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243983",
    },
    {
      lookupValue: "CounselorOfRealEstate",
      lookupDisplayName: "Counselor of Real Estate / CRE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243984",
    },
    {
      lookupValue: "ePRO",
      lookupDisplayName: "e-PRO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/e-PRO",
    },
    {
      lookupValue: "GeneralAccreditedAppraiser",
      lookupDisplayName: "General Accredited Appraiser / GAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243986",
    },
    {
      lookupValue: "GraduateRealtorInstitute",
      lookupDisplayName: "Graduate, REALTOR Institute / GRI",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243987",
    },
    {
      lookupValue: "MilitaryRelocationProfessional",
      lookupDisplayName: "Military Relocation Professional / MRP",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243988",
    },
    {
      lookupValue: "NARsGreenDesignation",
      lookupDisplayName: "NAR's Green Designation / GREEN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243989",
    },
    {
      lookupValue: "PerformanceManagementNetwork",
      lookupDisplayName: "Performance Management Network / PMN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243990",
    },
    {
      lookupValue: "PricingStrategyAdvisor",
      lookupDisplayName: "Pricing Strategy Advisor / PSA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243991",
    },
    {
      lookupValue: "RealEstateNegotiationExpert",
      lookupDisplayName: "Real Estate Negotiation Expert / RENE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243992",
    },
    {
      lookupValue: "RealtorAssociationCertifiedExecutive",
      lookupDisplayName: "REALTOR Association Certified Executive / RCE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243993",
    },
    {
      lookupValue: "ResidentialAccreditedAppraiser",
      lookupDisplayName: "Residential Accredited Appraiser / RAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243994",
    },
    {
      lookupValue: "ResortAndSecondHomePropertySpecialist",
      lookupDisplayName: "Resort & Second-Home Property Specialist / RSPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243995",
    },
    {
      lookupValue: "SellerRepresentativeSpecialist",
      lookupDisplayName: "Seller Representative Specialist / SRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243996",
    },
    {
      lookupValue: "SeniorsRealEstateSpecialist",
      lookupDisplayName: "Seniors Real Estate Specialist / SRES",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243997",
    },
    {
      lookupValue: "ShortSalesAndForeclosureResource",
      lookupDisplayName: "Short Sales & Foreclosure Resource / SFR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243998",
    },
    {
      lookupValue: "SocietyOfIndustrialAndOfficeRealtors",
      lookupDisplayName: "Society of Industrial and Office REALTORS / SIOR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243999",
    },
    {
      lookupValue: "TransnationalReferralCertification",
      lookupDisplayName: "Transnational Referral Certification / TRC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244000",
    },
  ],
  BuyerFinancing: [
    {
      lookupValue: "Assumed",
      lookupDisplayName: "Assumed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Assumed",
    },
    {
      lookupValue: "Cash",
      lookupDisplayName: "Cash",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cash",
    },
    {
      lookupValue: "Contract",
      lookupDisplayName: "Contract",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Contract",
    },
    {
      lookupValue: "Conventional",
      lookupDisplayName: "Conventional",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Conventional",
    },
    {
      lookupValue: "Fha",
      lookupDisplayName: "FHA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FHA",
    },
    {
      lookupValue: "FHA203b",
      lookupDisplayName: "FHA 203(b)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243965",
    },
    {
      lookupValue: "FHA203k",
      lookupDisplayName: "FHA 203(k)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243966",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29243967",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Private",
    },
    {
      lookupValue: "SellerFinancing",
      lookupDisplayName: "Seller Financing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Seller+Financing",
    },
    {
      lookupValue: "TrustDeed",
      lookupDisplayName: "Trust Deed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Trust+Deed",
    },
    {
      lookupValue: "Usda",
      lookupDisplayName: "USDA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/USDA",
    },
    {
      lookupValue: "Va",
      lookupDisplayName: "VA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VA",
    },
  ],
  ChangeType: [
    {
      lookupValue: "Active",
      lookupDisplayName: "Active",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Active",
    },
    {
      lookupValue: "ActiveUnderContract",
      lookupDisplayName: "Active Under Contract",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Active+Under+Contract",
    },
    {
      lookupValue: "BackOnMarket",
      lookupDisplayName: "Back On Market",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Back+On+Market",
    },
    {
      lookupValue: "Canceled",
      lookupDisplayName: "Canceled",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Canceled",
    },
    {
      lookupValue: "Closed",
      lookupDisplayName: "Closed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Closed",
    },
    {
      lookupValue: "Deleted",
      lookupDisplayName: "Deleted",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Deleted",
    },
    {
      lookupValue: "Expired",
      lookupDisplayName: "Expired",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Expired",
    },
    {
      lookupValue: "Hold",
      lookupDisplayName: "Hold",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hold",
    },
    {
      lookupValue: "NewListing",
      lookupDisplayName: "New Listing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/New+Listing",
    },
    {
      lookupValue: "Pending",
      lookupDisplayName: "Pending",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pending",
    },
    {
      lookupValue: "PriceChange",
      lookupDisplayName: "Price Change",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Price+Change",
    },
    {
      lookupValue: "Withdrawn",
      lookupDisplayName: "Withdrawn",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Withdrawn",
    },
  ],
  ClassName: [
    {
      lookupValue: "BusinessOpportunity",
      lookupDisplayName: "Business Opportunity",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Business+Opportunity",
    },
    {
      lookupValue: "CommercialLease",
      lookupDisplayName: "Commercial Lease",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Commercial+Lease",
    },
    {
      lookupValue: "CommercialSale",
      lookupDisplayName: "Commercial Sale",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Commercial+Sale",
    },
    {
      lookupValue: "Contacts",
      lookupDisplayName: "Contacts",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Contacts",
    },
    {
      lookupValue: "CrossProperty",
      lookupDisplayName: "Cross Property",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cross+Property",
    },
    {
      lookupValue: "Farm",
      lookupDisplayName: "Farm",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244043",
    },
    {
      lookupValue: "HistoryTransactional",
      lookupDisplayName: "History Transactional",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/History+Transactional",
    },
    {
      lookupValue: "Land",
      lookupDisplayName: "Land",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Land",
    },
    {
      lookupValue: "ManufacturedInPark",
      lookupDisplayName: "Manufactured In Park",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manufactured+In+Park",
    },
    {
      lookupValue: "Media",
      lookupDisplayName: "Media",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Media",
    },
    {
      lookupValue: "Member",
      lookupDisplayName: "Member",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Member",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Office",
    },
    {
      lookupValue: "OpenHouse",
      lookupDisplayName: "Open House",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+House",
    },
    {
      lookupValue: "Residential",
      lookupDisplayName: "Residential",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244051",
    },
    {
      lookupValue: "ResidentialIncome",
      lookupDisplayName: "Residential Income",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Residential+Income",
    },
    {
      lookupValue: "ResidentialLease",
      lookupDisplayName: "Residential Lease",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Residential+Lease",
    },
    {
      lookupValue: "SavedSearch",
      lookupDisplayName: "Saved Search",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Saved+Search",
    },
  ],
  CoBuyerAgentDesignation: [
    {
      lookupValue: "AccreditedBuyersRepresentative",
      lookupDisplayName: "Accredited Buyer's Representative / ABR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244408",
    },
    {
      lookupValue: "AccreditedLandConsultant",
      lookupDisplayName: "Accredited Land Consultant / ALC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244409",
    },
    {
      lookupValue: "AtHomeWithDiversity",
      lookupDisplayName: "At Home With Diversity / AHWD",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244410",
    },
    {
      lookupValue: "CertifiedCommercialInvestmentMember",
      lookupDisplayName: "Certified Commercial Investment Member / CCIM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244411",
    },
    {
      lookupValue: "CertifiedDistressedPropertyExpert",
      lookupDisplayName: "Certified Distressed Property Expert / CDPE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244412",
    },
    {
      lookupValue: "CertifiedInternationalPropertySpecialist",
      lookupDisplayName: "Certified International Property Specialist / CIPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244413",
    },
    {
      lookupValue: "CertifiedPropertyManager",
      lookupDisplayName: "Certified Property Manager / CPM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244414",
    },
    {
      lookupValue: "CertifiedRealEstateBrokerageManager",
      lookupDisplayName: "Certified Real Estate Brokerage Manager / CRB",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244415",
    },
    {
      lookupValue: "CertifiedRealEstateTeamSpecialist",
      lookupDisplayName: "Certified Real Estate Team Specialist / C-RETS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244416",
    },
    {
      lookupValue: "CertifiedResidentialSpecialist",
      lookupDisplayName: "Certified Residential Specialist / CRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244417",
    },
    {
      lookupValue: "CounselorOfRealEstate",
      lookupDisplayName: "Counselor of Real Estate / CRE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244418",
    },
    {
      lookupValue: "ePRO",
      lookupDisplayName: "e-PRO",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244419",
    },
    {
      lookupValue: "GeneralAccreditedAppraiser",
      lookupDisplayName: "General Accredited Appraiser / GAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244420",
    },
    {
      lookupValue: "GraduateRealtorInstitute",
      lookupDisplayName: "Graduate, REALTOR Institute / GRI",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244421",
    },
    {
      lookupValue: "MilitaryRelocationProfessional",
      lookupDisplayName: "Military Relocation Professional / MRP",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244422",
    },
    {
      lookupValue: "NARsGreenDesignation",
      lookupDisplayName: "NAR's Green Designation / GREEN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244423",
    },
    {
      lookupValue: "PerformanceManagementNetwork",
      lookupDisplayName: "Performance Management Network / PMN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244424",
    },
    {
      lookupValue: "PricingStrategyAdvisor",
      lookupDisplayName: "Pricing Strategy Advisor / PSA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244425",
    },
    {
      lookupValue: "RealEstateNegotiationExpert",
      lookupDisplayName: "Real Estate Negotiation Expert / RENE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244426",
    },
    {
      lookupValue: "RealtorAssociationCertifiedExecutive",
      lookupDisplayName: "REALTOR Association Certified Executive / RCE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244427",
    },
    {
      lookupValue: "ResidentialAccreditedAppraiser",
      lookupDisplayName: "Residential Accredited Appraiser / RAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244428",
    },
    {
      lookupValue: "ResortAndSecondHomePropertySpecialist",
      lookupDisplayName: "Resort & Second-Home Property Specialist / RSPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244429",
    },
    {
      lookupValue: "SellerRepresentativeSpecialist",
      lookupDisplayName: "Seller Representative Specialist / SRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244430",
    },
    {
      lookupValue: "SeniorsRealEstateSpecialist",
      lookupDisplayName: "Seniors Real Estate Specialist / SRES",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244431",
    },
    {
      lookupValue: "ShortSalesAndForeclosureResource",
      lookupDisplayName: "Short Sales & Foreclosure Resource / SFR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244432",
    },
    {
      lookupValue: "SocietyOfIndustrialAndOfficeRealtors",
      lookupDisplayName: "Society of Industrial and Office REALTORS / SIOR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244433",
    },
    {
      lookupValue: "TransnationalReferralCertification",
      lookupDisplayName: "Transnational Referral Certification / TRC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244434",
    },
  ],
  CoListAgentDesignation: [
    {
      lookupValue: "AccreditedBuyersRepresentative",
      lookupDisplayName: "Accredited Buyer's Representative / ABR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244436",
    },
    {
      lookupValue: "AccreditedLandConsultant",
      lookupDisplayName: "Accredited Land Consultant / ALC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244437",
    },
    {
      lookupValue: "AtHomeWithDiversity",
      lookupDisplayName: "At Home With Diversity / AHWD",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244438",
    },
    {
      lookupValue: "CertifiedCommercialInvestmentMember",
      lookupDisplayName: "Certified Commercial Investment Member / CCIM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244439",
    },
    {
      lookupValue: "CertifiedDistressedPropertyExpert",
      lookupDisplayName: "Certified Distressed Property Expert / CDPE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244440",
    },
    {
      lookupValue: "CertifiedInternationalPropertySpecialist",
      lookupDisplayName: "Certified International Property Specialist / CIPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244441",
    },
    {
      lookupValue: "CertifiedPropertyManager",
      lookupDisplayName: "Certified Property Manager / CPM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244442",
    },
    {
      lookupValue: "CertifiedRealEstateBrokerageManager",
      lookupDisplayName: "Certified Real Estate Brokerage Manager / CRB",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244443",
    },
    {
      lookupValue: "CertifiedRealEstateTeamSpecialist",
      lookupDisplayName: "Certified Real Estate Team Specialist / C-RETS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244444",
    },
    {
      lookupValue: "CertifiedResidentialSpecialist",
      lookupDisplayName: "Certified Residential Specialist / CRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244445",
    },
    {
      lookupValue: "CounselorOfRealEstate",
      lookupDisplayName: "Counselor of Real Estate / CRE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244446",
    },
    {
      lookupValue: "ePRO",
      lookupDisplayName: "e-PRO",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244447",
    },
    {
      lookupValue: "GeneralAccreditedAppraiser",
      lookupDisplayName: "General Accredited Appraiser / GAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244448",
    },
    {
      lookupValue: "GraduateRealtorInstitute",
      lookupDisplayName: "Graduate, REALTOR Institute / GRI",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244449",
    },
    {
      lookupValue: "MilitaryRelocationProfessional",
      lookupDisplayName: "Military Relocation Professional / MRP",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244450",
    },
    {
      lookupValue: "NARsGreenDesignation",
      lookupDisplayName: "NAR's Green Designation / GREEN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244451",
    },
    {
      lookupValue: "PerformanceManagementNetwork",
      lookupDisplayName: "Performance Management Network / PMN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244452",
    },
    {
      lookupValue: "PricingStrategyAdvisor",
      lookupDisplayName: "Pricing Strategy Advisor / PSA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244453",
    },
    {
      lookupValue: "RealEstateNegotiationExpert",
      lookupDisplayName: "Real Estate Negotiation Expert / RENE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244454",
    },
    {
      lookupValue: "RealtorAssociationCertifiedExecutive",
      lookupDisplayName: "REALTOR Association Certified Executive / RCE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244455",
    },
    {
      lookupValue: "ResidentialAccreditedAppraiser",
      lookupDisplayName: "Residential Accredited Appraiser / RAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244456",
    },
    {
      lookupValue: "ResortAndSecondHomePropertySpecialist",
      lookupDisplayName: "Resort & Second-Home Property Specialist / RSPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244457",
    },
    {
      lookupValue: "SellerRepresentativeSpecialist",
      lookupDisplayName: "Seller Representative Specialist / SRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244458",
    },
    {
      lookupValue: "SeniorsRealEstateSpecialist",
      lookupDisplayName: "Seniors Real Estate Specialist / SRES",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244459",
    },
    {
      lookupValue: "ShortSalesAndForeclosureResource",
      lookupDisplayName: "Short Sales & Foreclosure Resource / SFR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244460",
    },
    {
      lookupValue: "SocietyOfIndustrialAndOfficeRealtors",
      lookupDisplayName: "Society of Industrial and Office REALTORS / SIOR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244461",
    },
    {
      lookupValue: "TransnationalReferralCertification",
      lookupDisplayName: "Transnational Referral Certification / TRC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244462",
    },
  ],
  CommonInterest: [
    {
      lookupValue: "CommunityApartment",
      lookupDisplayName: "Community Apartment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Community+Apartment",
    },
    {
      lookupValue: "Condominium",
      lookupDisplayName: "Condominium",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244396",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244401",
    },
    {
      lookupValue: "PlannedDevelopment",
      lookupDisplayName: "Planned Development",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Planned+Development",
    },
    {
      lookupValue: "StockCooperative",
      lookupDisplayName: "Stock Cooperative",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244399",
    },
    {
      lookupValue: "Timeshare",
      lookupDisplayName: "Timeshare",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244400",
    },
  ],
  CommonWalls: [
    {
      lookupValue: "EndUnit",
      lookupDisplayName: "End Unit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/End+Unit",
    },
    {
      lookupValue: "NoCommonWalls",
      lookupDisplayName: "No Common Walls",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+Common+Walls",
    },
    {
      lookupValue: "NoOneAbove",
      lookupDisplayName: "No One Above",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+One+Above",
    },
    {
      lookupValue: "NoOneBelow",
      lookupDisplayName: "No One Below",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+One+Below",
    },
    {
      lookupValue: "OneCommonWall",
      lookupDisplayName: "1 Common Wall",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/1+Common+Wall",
    },
    {
      lookupValue: "TwoOrMoreCommonWalls",
      lookupDisplayName: "2+ Common Walls",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244057",
    },
  ],
  CommunityFeatures: [
    {
      lookupValue: "AirportRunway",
      lookupDisplayName: "Airport/Runway",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244536",
    },
    {
      lookupValue: "Clubhouse",
      lookupDisplayName: "Clubhouse",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244537",
    },
    {
      lookupValue: "Curbs",
      lookupDisplayName: "Curbs",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Curbs",
    },
    {
      lookupValue: "Fishing",
      lookupDisplayName: "Fishing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fishing",
    },
    {
      lookupValue: "FitnessCenter",
      lookupDisplayName: "Fitness Center",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244540",
    },
    {
      lookupValue: "Gated",
      lookupDisplayName: "Gated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244541",
    },
    {
      lookupValue: "Golf",
      lookupDisplayName: "Golf",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Golf",
    },
    {
      lookupValue: "Lake",
      lookupDisplayName: "Lake",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244543",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244544",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244545",
    },
    {
      lookupValue: "Park",
      lookupDisplayName: "Park",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244546",
    },
    {
      lookupValue: "Playground",
      lookupDisplayName: "Playground",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244547",
    },
    {
      lookupValue: "Pool",
      lookupDisplayName: "Pool",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244548",
    },
    {
      lookupValue: "Racquetball",
      lookupDisplayName: "Racquetball",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244549",
    },
    {
      lookupValue: "Restaurant",
      lookupDisplayName: "Restaurant",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244550",
    },
    {
      lookupValue: "Sidewalks",
      lookupDisplayName: "Sidewalks",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sidewalks",
    },
    {
      lookupValue: "Stables",
      lookupDisplayName: "Stable(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244552",
    },
    {
      lookupValue: "StreetLights",
      lookupDisplayName: "Street Lights",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Street+Lights",
    },
    {
      lookupValue: "Suburban",
      lookupDisplayName: "Suburban",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Suburban",
    },
    {
      lookupValue: "TennisCourts",
      lookupDisplayName: "Tennis Court(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244555",
    },
  ],
  CompensationType: [
    {
      lookupValue: "Dollars",
      lookupDisplayName: "$",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244063",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244066",
    },
    {
      lookupValue: "Percent",
      lookupDisplayName: "%",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244064",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244065",
    },
  ],
  Concessions: [
    {
      lookupValue: "CallListingAgent",
      lookupDisplayName: "Call Listing Agent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Call+Listing+Agent",
    },
    {
      lookupValue: "No",
      lookupDisplayName: "No",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No",
    },
    {
      lookupValue: "Yes",
      lookupDisplayName: "Yes",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Yes",
    },
  ],
  ConstructionMaterials: [
    {
      lookupValue: "Adobe",
      lookupDisplayName: "Adobe",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Adobe",
    },
    {
      lookupValue: "AluminumSiding",
      lookupDisplayName: "Aluminum Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aluminum+Siding",
    },
    {
      lookupValue: "Asbestos",
      lookupDisplayName: "Asbestos",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Asbestos",
    },
    {
      lookupValue: "Asphalt",
      lookupDisplayName: "Asphalt",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Asphalt",
    },
    {
      lookupValue: "AtticCrawlHatchwaysInsulated",
      lookupDisplayName: "Attic/Crawl Hatchway(s) Insulated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244076",
    },
    {
      lookupValue: "BattsInsulation",
      lookupDisplayName: "Batts Insulation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Batts+Insulation",
    },
    {
      lookupValue: "Block",
      lookupDisplayName: "Block",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Block",
    },
    {
      lookupValue: "BlownInInsulation",
      lookupDisplayName: "Blown-In Insulation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Blown-In+Insulation",
    },
    {
      lookupValue: "BoardAndBattenSiding",
      lookupDisplayName: "Board & Batten Siding",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244080",
    },
    {
      lookupValue: "Brick",
      lookupDisplayName: "Brick",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Brick",
    },
    {
      lookupValue: "BrickVeneer",
      lookupDisplayName: "Brick Veneer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Brick+Veneer",
    },
    {
      lookupValue: "Cedar",
      lookupDisplayName: "Cedar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cedar",
    },
    {
      lookupValue: "CementSiding",
      lookupDisplayName: "Cement Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cement+Siding",
    },
    {
      lookupValue: "Clapboard",
      lookupDisplayName: "Clapboard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Clapboard",
    },
    {
      lookupValue: "Concrete",
      lookupDisplayName: "Concrete",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Concrete",
    },
    {
      lookupValue: "DuctsProfessionallyAirSealed",
      lookupDisplayName: "Ducts Professionally Air-Sealed",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Ducts+Professionally+Air-Sealed",
    },
    {
      lookupValue: "ExteriorDuctWorkIsInsulated",
      lookupDisplayName: "Exterior Duct-Work is Insulated",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Exterior+Duct-Work+is+Insulated",
    },
    {
      lookupValue: "FiberCement",
      lookupDisplayName: "Fiber Cement",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fiber+Cement",
    },
    {
      lookupValue: "FiberglassSiding",
      lookupDisplayName: "Fiberglass Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fiberglass+Siding",
    },
    {
      lookupValue: "FoamInsulation",
      lookupDisplayName: "Foam Insulation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Foam+Insulation",
    },
    {
      lookupValue: "Frame",
      lookupDisplayName: "Frame",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Frame",
    },
    {
      lookupValue: "Glass",
      lookupDisplayName: "Glass",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Glass",
    },
    {
      lookupValue: "HardiplankType",
      lookupDisplayName: "HardiPlank Type",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HardiPlank+Type",
    },
    {
      lookupValue: "IcatRecessedLighting",
      lookupDisplayName: "ICAT Recessed Lighting",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ICAT+Recessed+Lighting",
    },
    {
      lookupValue: "InsulatedConcreteForms",
      lookupDisplayName: "ICFs (Insulated Concrete Forms)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244096",
    },
    {
      lookupValue: "LapSiding",
      lookupDisplayName: "Lap Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lap+Siding",
    },
    {
      lookupValue: "Log",
      lookupDisplayName: "Log",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Log",
    },
    {
      lookupValue: "LogSiding",
      lookupDisplayName: "Log Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Log+Siding",
    },
    {
      lookupValue: "LowVocInsulation",
      lookupDisplayName: "Low VOC Insulation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Low+VOC+Insulation",
    },
    {
      lookupValue: "Masonite",
      lookupDisplayName: "Masonite",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Masonite",
    },
    {
      lookupValue: "MetalSiding",
      lookupDisplayName: "Metal Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Metal+Siding",
    },
    {
      lookupValue: "NaturalBuilding",
      lookupDisplayName: "Natural Building",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Natural+Building",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244104",
    },
    {
      lookupValue: "Plaster",
      lookupDisplayName: "Plaster",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Plaster",
    },
    {
      lookupValue: "RadiantBarrier",
      lookupDisplayName: "Radiant Barrier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Radiant+Barrier",
    },
    {
      lookupValue: "RammedEarth",
      lookupDisplayName: "Rammed Earth",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rammed+Earth",
    },
    {
      lookupValue: "RecycledBioBasedInsulation",
      lookupDisplayName: "Recycled/Bio-Based Insulation",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244108",
    },
    {
      lookupValue: "RedwoodSiding",
      lookupDisplayName: "Redwood Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Redwood+Siding",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244110",
    },
    {
      lookupValue: "ShakeSiding",
      lookupDisplayName: "Shake Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shake+Siding",
    },
    {
      lookupValue: "ShingleSiding",
      lookupDisplayName: "Shingle Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shingle+Siding",
    },
    {
      lookupValue: "SlumpBlock",
      lookupDisplayName: "Slump Block",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Slump+Block",
    },
    {
      lookupValue: "SprayFoamInsulation",
      lookupDisplayName: "Spray Foam Insulation",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Spray+Foam+Insulation",
    },
    {
      lookupValue: "SteelSiding",
      lookupDisplayName: "Steel Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Steel+Siding",
    },
    {
      lookupValue: "Stone",
      lookupDisplayName: "Stone",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stone",
    },
    {
      lookupValue: "StoneVeneer",
      lookupDisplayName: "Stone Veneer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stone+Veneer",
    },
    {
      lookupValue: "Straw",
      lookupDisplayName: "Straw",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Straw",
    },
    {
      lookupValue: "Stucco",
      lookupDisplayName: "Stucco",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stucco",
    },
    {
      lookupValue: "SyntheticStucco",
      lookupDisplayName: "Synthetic Stucco",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Synthetic+Stucco",
    },
    {
      lookupValue: "Unknown",
      lookupDisplayName: "Unknown",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unknown",
    },
    {
      lookupValue: "VerticalSiding",
      lookupDisplayName: "Vertical Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vertical+Siding",
    },
    {
      lookupValue: "VinylSiding",
      lookupDisplayName: "Vinyl Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vinyl+Siding",
    },
    {
      lookupValue: "WoodSiding",
      lookupDisplayName: "Wood Siding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wood+Siding",
    },
  ],
  ContactListingPreference: [
    {
      lookupValue: "Discard",
      lookupDisplayName: "Discard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Discard",
    },
    {
      lookupValue: "Favorite",
      lookupDisplayName: "Favorite",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Favorite",
    },
    {
      lookupValue: "Possibility",
      lookupDisplayName: "Possibility",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Possibility",
    },
  ],
  ContactStatus: [
    {
      lookupValue: "Active",
      lookupDisplayName: "Active",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244403",
    },
    {
      lookupValue: "Deleted",
      lookupDisplayName: "Deleted",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244404",
    },
    {
      lookupValue: "Inactive",
      lookupDisplayName: "Inactive",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244405",
    },
    {
      lookupValue: "OnVacation",
      lookupDisplayName: "On Vacation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/On+Vacation",
    },
  ],
  ContactType: [
    {
      lookupValue: "Business",
      lookupDisplayName: "Business",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Business",
    },
    {
      lookupValue: "Family",
      lookupDisplayName: "Family",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Family",
    },
    {
      lookupValue: "Friend",
      lookupDisplayName: "Friend",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Friend",
    },
    {
      lookupValue: "Lead",
      lookupDisplayName: "Lead",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lead",
    },
    {
      lookupValue: "Prospect",
      lookupDisplayName: "Prospect",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Prospect",
    },
    {
      lookupValue: "ReadyToBuy",
      lookupDisplayName: "Ready to Buy",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ready+to+Buy",
    },
  ],
  Cooling: [
    {
      lookupValue: "AtticFan",
      lookupDisplayName: "Attic Fan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Attic+Fan",
    },
    {
      lookupValue: "CeilingFans",
      lookupDisplayName: "Ceiling Fan(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244511",
    },
    {
      lookupValue: "CentralAir",
      lookupDisplayName: "Central Air",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Central+Air",
    },
    {
      lookupValue: "Dual",
      lookupDisplayName: "Dual",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dual",
    },
    {
      lookupValue: "Ductless",
      lookupDisplayName: "Ductless",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ductless",
    },
    {
      lookupValue: "Electric",
      lookupDisplayName: "Electric",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244515",
    },
    {
      lookupValue: "EnergyStarQualifiedEquipment",
      lookupDisplayName: "ENERGY STAR Qualified Equipment",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Equipment",
    },
    {
      lookupValue: "EvaporativeCooling",
      lookupDisplayName: "Evaporative Cooling",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Evaporative+Cooling",
    },
    {
      lookupValue: "ExhaustFan",
      lookupDisplayName: "Exhaust Fan",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244518",
    },
    {
      lookupValue: "Gas",
      lookupDisplayName: "Gas",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244519",
    },
    {
      lookupValue: "Geothermal",
      lookupDisplayName: "Geothermal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Geothermal",
    },
    {
      lookupValue: "HeatPump",
      lookupDisplayName: "Heat Pump",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Heat+Pump",
    },
    {
      lookupValue: "HumidityControl",
      lookupDisplayName: "Humidity Control",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Humidity+Control",
    },
    {
      lookupValue: "MultiUnits",
      lookupDisplayName: "Multi Units",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Multi+Units",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244524",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244525",
    },
    {
      lookupValue: "RoofTurbines",
      lookupDisplayName: "Roof Turbine(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244526",
    },
    {
      lookupValue: "SeparateMeters",
      lookupDisplayName: "Separate Meters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Separate+Meters",
    },
    {
      lookupValue: "VariesByUnit",
      lookupDisplayName: "Varies by Unit",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244528",
    },
    {
      lookupValue: "WallUnits",
      lookupDisplayName: "Wall Unit(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244530",
    },
    {
      lookupValue: "WallWindowUnits",
      lookupDisplayName: "Wall/Window Unit(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244529",
    },
    {
      lookupValue: "WholeHouseFan",
      lookupDisplayName: "Whole House Fan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Whole+House+Fan",
    },
    {
      lookupValue: "WindowUnits",
      lookupDisplayName: "Window Unit(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244532",
    },
    {
      lookupValue: "Zoned",
      lookupDisplayName: "Zoned",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Zoned",
    },
  ],
  Country: [
    {
      lookupValue: "AD",
      lookupDisplayName: "AD",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AD",
    },
    {
      lookupValue: "AE",
      lookupDisplayName: "AE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AE",
    },
    {
      lookupValue: "AF",
      lookupDisplayName: "AF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AF",
    },
    {
      lookupValue: "AG",
      lookupDisplayName: "AG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AG",
    },
    {
      lookupValue: "AI",
      lookupDisplayName: "AI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AI",
    },
    {
      lookupValue: "AL",
      lookupDisplayName: "AL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AL",
    },
    {
      lookupValue: "AM",
      lookupDisplayName: "AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AM",
    },
    {
      lookupValue: "AN",
      lookupDisplayName: "AN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AN",
    },
    {
      lookupValue: "AO",
      lookupDisplayName: "AO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AO",
    },
    {
      lookupValue: "AQ",
      lookupDisplayName: "AQ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AQ",
    },
    {
      lookupValue: "AR",
      lookupDisplayName: "AR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AR",
    },
    {
      lookupValue: "AS",
      lookupDisplayName: "AS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AS",
    },
    {
      lookupValue: "AT",
      lookupDisplayName: "AT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AT",
    },
    {
      lookupValue: "AU",
      lookupDisplayName: "AU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AU",
    },
    {
      lookupValue: "AW",
      lookupDisplayName: "AW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AW",
    },
    {
      lookupValue: "AX",
      lookupDisplayName: "AX",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AX",
    },
    {
      lookupValue: "AZ",
      lookupDisplayName: "AZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AZ",
    },
    {
      lookupValue: "BA",
      lookupDisplayName: "BA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BA",
    },
    {
      lookupValue: "BB",
      lookupDisplayName: "BB",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BB",
    },
    {
      lookupValue: "BD",
      lookupDisplayName: "BD",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BD",
    },
    {
      lookupValue: "BE",
      lookupDisplayName: "BE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BE",
    },
    {
      lookupValue: "BF",
      lookupDisplayName: "BF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BF",
    },
    {
      lookupValue: "BG",
      lookupDisplayName: "BG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BG",
    },
    {
      lookupValue: "BH",
      lookupDisplayName: "BH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BH",
    },
    {
      lookupValue: "BI",
      lookupDisplayName: "BI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BI",
    },
    {
      lookupValue: "BJ",
      lookupDisplayName: "BJ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BJ",
    },
    {
      lookupValue: "BL",
      lookupDisplayName: "BL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BL",
    },
    {
      lookupValue: "BM",
      lookupDisplayName: "BM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BM",
    },
    {
      lookupValue: "BN",
      lookupDisplayName: "BN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BN",
    },
    {
      lookupValue: "BO",
      lookupDisplayName: "BO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BO",
    },
    {
      lookupValue: "BR",
      lookupDisplayName: "BR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BR",
    },
    {
      lookupValue: "BS",
      lookupDisplayName: "BS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BS",
    },
    {
      lookupValue: "BT",
      lookupDisplayName: "BT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BT",
    },
    {
      lookupValue: "BV",
      lookupDisplayName: "BV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BV",
    },
    {
      lookupValue: "BW",
      lookupDisplayName: "BW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BW",
    },
    {
      lookupValue: "BY",
      lookupDisplayName: "BY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BY",
    },
    {
      lookupValue: "BZ",
      lookupDisplayName: "BZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BZ",
    },
    {
      lookupValue: "CA",
      lookupDisplayName: "CA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CA",
    },
    {
      lookupValue: "CC",
      lookupDisplayName: "CC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CC",
    },
    {
      lookupValue: "CD",
      lookupDisplayName: "CD",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CD",
    },
    {
      lookupValue: "CF",
      lookupDisplayName: "CF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CF",
    },
    {
      lookupValue: "CG",
      lookupDisplayName: "CG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CG",
    },
    {
      lookupValue: "CH",
      lookupDisplayName: "CH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CH",
    },
    {
      lookupValue: "CI",
      lookupDisplayName: "CI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CI",
    },
    {
      lookupValue: "CK",
      lookupDisplayName: "CK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CK",
    },
    {
      lookupValue: "CL",
      lookupDisplayName: "CL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CL",
    },
    {
      lookupValue: "CM",
      lookupDisplayName: "CM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CM",
    },
    {
      lookupValue: "CN",
      lookupDisplayName: "CN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CN",
    },
    {
      lookupValue: "CO",
      lookupDisplayName: "CO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CO",
    },
    {
      lookupValue: "CR",
      lookupDisplayName: "CR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CR",
    },
    {
      lookupValue: "CU",
      lookupDisplayName: "CU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CU",
    },
    {
      lookupValue: "CV",
      lookupDisplayName: "CV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CV",
    },
    {
      lookupValue: "CX",
      lookupDisplayName: "CX",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CX",
    },
    {
      lookupValue: "CY",
      lookupDisplayName: "CY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CY",
    },
    {
      lookupValue: "CZ",
      lookupDisplayName: "CZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CZ",
    },
    {
      lookupValue: "DE",
      lookupDisplayName: "DE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DE",
    },
    {
      lookupValue: "DJ",
      lookupDisplayName: "DJ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DJ",
    },
    {
      lookupValue: "DK",
      lookupDisplayName: "DK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DK",
    },
    {
      lookupValue: "DM",
      lookupDisplayName: "DM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DM",
    },
    {
      lookupValue: "DO",
      lookupDisplayName: "DO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DO",
    },
    {
      lookupValue: "DZ",
      lookupDisplayName: "DZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DZ",
    },
    {
      lookupValue: "EC",
      lookupDisplayName: "EC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/EC",
    },
    {
      lookupValue: "EE",
      lookupDisplayName: "EE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/EE",
    },
    {
      lookupValue: "EG",
      lookupDisplayName: "EG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/EG",
    },
    {
      lookupValue: "EH",
      lookupDisplayName: "EH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/EH",
    },
    {
      lookupValue: "ER",
      lookupDisplayName: "ER",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ER",
    },
    {
      lookupValue: "ES",
      lookupDisplayName: "ES",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ES",
    },
    {
      lookupValue: "ET",
      lookupDisplayName: "ET",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ET",
    },
    {
      lookupValue: "FI",
      lookupDisplayName: "FI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FI",
    },
    {
      lookupValue: "FJ",
      lookupDisplayName: "FJ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FJ",
    },
    {
      lookupValue: "FK",
      lookupDisplayName: "FK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FK",
    },
    {
      lookupValue: "FM",
      lookupDisplayName: "FM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FM",
    },
    {
      lookupValue: "FO",
      lookupDisplayName: "FO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FO",
    },
    {
      lookupValue: "FR",
      lookupDisplayName: "FR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FR",
    },
    {
      lookupValue: "GA",
      lookupDisplayName: "GA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GA",
    },
    {
      lookupValue: "GB",
      lookupDisplayName: "GB",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GB",
    },
    {
      lookupValue: "GD",
      lookupDisplayName: "GD",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GD",
    },
    {
      lookupValue: "GE",
      lookupDisplayName: "GE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GE",
    },
    {
      lookupValue: "GF",
      lookupDisplayName: "GF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GF",
    },
    {
      lookupValue: "GG",
      lookupDisplayName: "GG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GG",
    },
    {
      lookupValue: "GH",
      lookupDisplayName: "GH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GH",
    },
    {
      lookupValue: "GI",
      lookupDisplayName: "GI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GI",
    },
    {
      lookupValue: "GL",
      lookupDisplayName: "GL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GL",
    },
    {
      lookupValue: "GM",
      lookupDisplayName: "GM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GM",
    },
    {
      lookupValue: "GN",
      lookupDisplayName: "GN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GN",
    },
    {
      lookupValue: "GP",
      lookupDisplayName: "GP",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GP",
    },
    {
      lookupValue: "GQ",
      lookupDisplayName: "GQ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GQ",
    },
    {
      lookupValue: "GR",
      lookupDisplayName: "GR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GR",
    },
    {
      lookupValue: "GS",
      lookupDisplayName: "GS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GS",
    },
    {
      lookupValue: "GT",
      lookupDisplayName: "GT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GT",
    },
    {
      lookupValue: "GU",
      lookupDisplayName: "GU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GU",
    },
    {
      lookupValue: "GW",
      lookupDisplayName: "GW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GW",
    },
    {
      lookupValue: "GY",
      lookupDisplayName: "GY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GY",
    },
    {
      lookupValue: "HK",
      lookupDisplayName: "HK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HK",
    },
    {
      lookupValue: "HM",
      lookupDisplayName: "HM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HM",
    },
    {
      lookupValue: "HN",
      lookupDisplayName: "HN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HN",
    },
    {
      lookupValue: "HR",
      lookupDisplayName: "HR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HR",
    },
    {
      lookupValue: "HT",
      lookupDisplayName: "HT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HT",
    },
    {
      lookupValue: "HU",
      lookupDisplayName: "HU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HU",
    },
    {
      lookupValue: "ID",
      lookupDisplayName: "ID",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ID",
    },
    {
      lookupValue: "IE",
      lookupDisplayName: "IE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IE",
    },
    {
      lookupValue: "IL",
      lookupDisplayName: "IL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IL",
    },
    {
      lookupValue: "IM",
      lookupDisplayName: "IM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IM",
    },
    {
      lookupValue: "IN",
      lookupDisplayName: "IN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IN",
    },
    {
      lookupValue: "IO",
      lookupDisplayName: "IO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IO",
    },
    {
      lookupValue: "IQ",
      lookupDisplayName: "IQ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IQ",
    },
    {
      lookupValue: "IR",
      lookupDisplayName: "IR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IR",
    },
    {
      lookupValue: "IS",
      lookupDisplayName: "IS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IS",
    },
    {
      lookupValue: "IT",
      lookupDisplayName: "IT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IT",
    },
    {
      lookupValue: "JE",
      lookupDisplayName: "JE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/JE",
    },
    {
      lookupValue: "JM",
      lookupDisplayName: "JM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/JM",
    },
    {
      lookupValue: "JO",
      lookupDisplayName: "JO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/JO",
    },
    {
      lookupValue: "JP",
      lookupDisplayName: "JP",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/JP",
    },
    {
      lookupValue: "KE",
      lookupDisplayName: "KE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KE",
    },
    {
      lookupValue: "KG",
      lookupDisplayName: "KG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KG",
    },
    {
      lookupValue: "KH",
      lookupDisplayName: "KH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KH",
    },
    {
      lookupValue: "KI",
      lookupDisplayName: "KI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KI",
    },
    {
      lookupValue: "KM",
      lookupDisplayName: "KM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KM",
    },
    {
      lookupValue: "KN",
      lookupDisplayName: "KN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KN",
    },
    {
      lookupValue: "KP",
      lookupDisplayName: "KP",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KP",
    },
    {
      lookupValue: "KR",
      lookupDisplayName: "KR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KR",
    },
    {
      lookupValue: "KW",
      lookupDisplayName: "KW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KW",
    },
    {
      lookupValue: "KY",
      lookupDisplayName: "KY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KY",
    },
    {
      lookupValue: "KZ",
      lookupDisplayName: "KZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KZ",
    },
    {
      lookupValue: "LA",
      lookupDisplayName: "LA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LA",
    },
    {
      lookupValue: "LB",
      lookupDisplayName: "LB",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LB",
    },
    {
      lookupValue: "LC",
      lookupDisplayName: "LC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LC",
    },
    {
      lookupValue: "LI",
      lookupDisplayName: "LI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LI",
    },
    {
      lookupValue: "LK",
      lookupDisplayName: "LK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LK",
    },
    {
      lookupValue: "LR",
      lookupDisplayName: "LR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LR",
    },
    {
      lookupValue: "LS",
      lookupDisplayName: "LS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LS",
    },
    {
      lookupValue: "LT",
      lookupDisplayName: "LT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LT",
    },
    {
      lookupValue: "LU",
      lookupDisplayName: "LU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LU",
    },
    {
      lookupValue: "LV",
      lookupDisplayName: "LV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LV",
    },
    {
      lookupValue: "LY",
      lookupDisplayName: "LY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LY",
    },
    {
      lookupValue: "MA",
      lookupDisplayName: "MA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MA",
    },
    {
      lookupValue: "MC",
      lookupDisplayName: "MC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MC",
    },
    {
      lookupValue: "MD",
      lookupDisplayName: "MD",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MD",
    },
    {
      lookupValue: "ME",
      lookupDisplayName: "ME",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ME",
    },
    {
      lookupValue: "MF",
      lookupDisplayName: "MF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MF",
    },
    {
      lookupValue: "MG",
      lookupDisplayName: "MG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MG",
    },
    {
      lookupValue: "MH",
      lookupDisplayName: "MH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MH",
    },
    {
      lookupValue: "MK",
      lookupDisplayName: "MK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MK",
    },
    {
      lookupValue: "ML",
      lookupDisplayName: "ML",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ML",
    },
    {
      lookupValue: "MM",
      lookupDisplayName: "MM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MM",
    },
    {
      lookupValue: "MN",
      lookupDisplayName: "MN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MN",
    },
    {
      lookupValue: "MO",
      lookupDisplayName: "MO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MO",
    },
    {
      lookupValue: "MP",
      lookupDisplayName: "MP",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MP",
    },
    {
      lookupValue: "MQ",
      lookupDisplayName: "MQ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MQ",
    },
    {
      lookupValue: "MR",
      lookupDisplayName: "MR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MR",
    },
    {
      lookupValue: "MS",
      lookupDisplayName: "MS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MS",
    },
    {
      lookupValue: "MT",
      lookupDisplayName: "MT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MT",
    },
    {
      lookupValue: "MU",
      lookupDisplayName: "MU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MU",
    },
    {
      lookupValue: "MV",
      lookupDisplayName: "MV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MV",
    },
    {
      lookupValue: "MW",
      lookupDisplayName: "MW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MW",
    },
    {
      lookupValue: "MX",
      lookupDisplayName: "MX",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MX",
    },
    {
      lookupValue: "MY",
      lookupDisplayName: "MY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MY",
    },
    {
      lookupValue: "MZ",
      lookupDisplayName: "MZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MZ",
    },
    {
      lookupValue: "NA",
      lookupDisplayName: "NA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NA",
    },
    {
      lookupValue: "NC",
      lookupDisplayName: "NC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NC",
    },
    {
      lookupValue: "NE",
      lookupDisplayName: "NE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NE",
    },
    {
      lookupValue: "NF",
      lookupDisplayName: "NF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NF",
    },
    {
      lookupValue: "NG",
      lookupDisplayName: "NG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NG",
    },
    {
      lookupValue: "NI",
      lookupDisplayName: "NI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NI",
    },
    {
      lookupValue: "NL",
      lookupDisplayName: "NL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NL",
    },
    {
      lookupValue: "NP",
      lookupDisplayName: "NP",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NP",
    },
    {
      lookupValue: "NR",
      lookupDisplayName: "NR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NR",
    },
    {
      lookupValue: "NU",
      lookupDisplayName: "NU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NU",
    },
    {
      lookupValue: "NZ",
      lookupDisplayName: "NZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NZ",
    },
    {
      lookupValue: "OM",
      lookupDisplayName: "OM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/OM",
    },
    {
      lookupValue: "OT",
      lookupDisplayName: "OT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/OT",
    },
    {
      lookupValue: "PA",
      lookupDisplayName: "PA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PA",
    },
    {
      lookupValue: "PE",
      lookupDisplayName: "PE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PE",
    },
    {
      lookupValue: "PF",
      lookupDisplayName: "PF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PF",
    },
    {
      lookupValue: "PG",
      lookupDisplayName: "PG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PG",
    },
    {
      lookupValue: "PH",
      lookupDisplayName: "PH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PH",
    },
    {
      lookupValue: "PK",
      lookupDisplayName: "PK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PK",
    },
    {
      lookupValue: "PL",
      lookupDisplayName: "PL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PL",
    },
    {
      lookupValue: "PM",
      lookupDisplayName: "PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PM",
    },
    {
      lookupValue: "PN",
      lookupDisplayName: "PN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PN",
    },
    {
      lookupValue: "PR",
      lookupDisplayName: "PR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PR",
    },
    {
      lookupValue: "PS",
      lookupDisplayName: "PS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PS",
    },
    {
      lookupValue: "PT",
      lookupDisplayName: "PT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PT",
    },
    {
      lookupValue: "PW",
      lookupDisplayName: "PW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PW",
    },
    {
      lookupValue: "PY",
      lookupDisplayName: "PY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PY",
    },
    {
      lookupValue: "QA",
      lookupDisplayName: "QA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/QA",
    },
    {
      lookupValue: "RE",
      lookupDisplayName: "RE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RE",
    },
    {
      lookupValue: "RO",
      lookupDisplayName: "RO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RO",
    },
    {
      lookupValue: "RS",
      lookupDisplayName: "RS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RS",
    },
    {
      lookupValue: "RU",
      lookupDisplayName: "RU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RU",
    },
    {
      lookupValue: "RW",
      lookupDisplayName: "RW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RW",
    },
    {
      lookupValue: "SA",
      lookupDisplayName: "SA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SA",
    },
    {
      lookupValue: "SB",
      lookupDisplayName: "SB",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SB",
    },
    {
      lookupValue: "SC",
      lookupDisplayName: "SC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SC",
    },
    {
      lookupValue: "SD",
      lookupDisplayName: "SD",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SD",
    },
    {
      lookupValue: "SE",
      lookupDisplayName: "SE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SE",
    },
    {
      lookupValue: "SG",
      lookupDisplayName: "SG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SG",
    },
    {
      lookupValue: "SH",
      lookupDisplayName: "SH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SH",
    },
    {
      lookupValue: "SI",
      lookupDisplayName: "SI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SI",
    },
    {
      lookupValue: "SJ",
      lookupDisplayName: "SJ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SJ",
    },
    {
      lookupValue: "SK",
      lookupDisplayName: "SK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SK",
    },
    {
      lookupValue: "SL",
      lookupDisplayName: "SL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SL",
    },
    {
      lookupValue: "SM",
      lookupDisplayName: "SM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SM",
    },
    {
      lookupValue: "SN",
      lookupDisplayName: "SN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SN",
    },
    {
      lookupValue: "SO",
      lookupDisplayName: "SO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SO",
    },
    {
      lookupValue: "SR",
      lookupDisplayName: "SR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SR",
    },
    {
      lookupValue: "ST",
      lookupDisplayName: "ST",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ST",
    },
    {
      lookupValue: "SV",
      lookupDisplayName: "SV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SV",
    },
    {
      lookupValue: "SY",
      lookupDisplayName: "SY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SY",
    },
    {
      lookupValue: "SZ",
      lookupDisplayName: "SZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SZ",
    },
    {
      lookupValue: "TC",
      lookupDisplayName: "TC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TC",
    },
    {
      lookupValue: "TD",
      lookupDisplayName: "TD",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TD",
    },
    {
      lookupValue: "TF",
      lookupDisplayName: "TF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TF",
    },
    {
      lookupValue: "TG",
      lookupDisplayName: "TG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TG",
    },
    {
      lookupValue: "TH",
      lookupDisplayName: "TH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TH",
    },
    {
      lookupValue: "TJ",
      lookupDisplayName: "TJ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TJ",
    },
    {
      lookupValue: "TK",
      lookupDisplayName: "TK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TK",
    },
    {
      lookupValue: "TL",
      lookupDisplayName: "TL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TL",
    },
    {
      lookupValue: "TM",
      lookupDisplayName: "TM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TM",
    },
    {
      lookupValue: "TN",
      lookupDisplayName: "TN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TN",
    },
    {
      lookupValue: "TO",
      lookupDisplayName: "TO",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TO",
    },
    {
      lookupValue: "TR",
      lookupDisplayName: "TR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TR",
    },
    {
      lookupValue: "TT",
      lookupDisplayName: "TT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TT",
    },
    {
      lookupValue: "TV",
      lookupDisplayName: "TV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TV",
    },
    {
      lookupValue: "TW",
      lookupDisplayName: "TW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TW",
    },
    {
      lookupValue: "TZ",
      lookupDisplayName: "TZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TZ",
    },
    {
      lookupValue: "UA",
      lookupDisplayName: "UA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/UA",
    },
    {
      lookupValue: "UG",
      lookupDisplayName: "UG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/UG",
    },
    {
      lookupValue: "UM",
      lookupDisplayName: "UM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/UM",
    },
    {
      lookupValue: "US",
      lookupDisplayName: "US",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/US",
    },
    {
      lookupValue: "UY",
      lookupDisplayName: "UY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/UY",
    },
    {
      lookupValue: "UZ",
      lookupDisplayName: "UZ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/UZ",
    },
    {
      lookupValue: "VA",
      lookupDisplayName: "VA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244365",
    },
    {
      lookupValue: "VC",
      lookupDisplayName: "VC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VC",
    },
    {
      lookupValue: "VE",
      lookupDisplayName: "VE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VE",
    },
    {
      lookupValue: "VG",
      lookupDisplayName: "VG",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VG",
    },
    {
      lookupValue: "VI",
      lookupDisplayName: "VI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VI",
    },
    {
      lookupValue: "VN",
      lookupDisplayName: "VN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VN",
    },
    {
      lookupValue: "VU",
      lookupDisplayName: "VU",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VU",
    },
    {
      lookupValue: "WF",
      lookupDisplayName: "WF",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/WF",
    },
    {
      lookupValue: "WS",
      lookupDisplayName: "WS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/WS",
    },
    {
      lookupValue: "YE",
      lookupDisplayName: "YE",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/YE",
    },
    {
      lookupValue: "YT",
      lookupDisplayName: "YT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/YT",
    },
    {
      lookupValue: "ZA",
      lookupDisplayName: "ZA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ZA",
    },
    {
      lookupValue: "ZM",
      lookupDisplayName: "ZM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ZM",
    },
    {
      lookupValue: "ZW",
      lookupDisplayName: "ZW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ZW",
    },
  ],
  CurrentFinancing: [
    {
      lookupValue: "Assumable",
      lookupDisplayName: "Assumable",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Assumable",
    },
    {
      lookupValue: "Contract",
      lookupDisplayName: "Contract",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244381",
    },
    {
      lookupValue: "Conventional",
      lookupDisplayName: "Conventional",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244382",
    },
    {
      lookupValue: "Fha",
      lookupDisplayName: "FHA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244383",
    },
    {
      lookupValue: "FHA203b",
      lookupDisplayName: "FHA 203(b)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244384",
    },
    {
      lookupValue: "FHA203k",
      lookupDisplayName: "FHA 203(k)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244385",
    },
    {
      lookupValue: "LeasedRenewables",
      lookupDisplayName: "Leased Renewables",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Leased+Renewables",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/None",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244388",
    },
    {
      lookupValue: "PowerPurchaseAgreement",
      lookupDisplayName: "Power Purchase Agreement",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Power+Purchase+Agreement",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244390",
    },
    {
      lookupValue: "PropertyAssessedCleanEnergy",
      lookupDisplayName: "Property-Assessed Clean Energy",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Property-Assessed+Clean+Energy",
    },
    {
      lookupValue: "TrustDeed",
      lookupDisplayName: "Trust Deed",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244392",
    },
    {
      lookupValue: "Usda",
      lookupDisplayName: "USDA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244393",
    },
    {
      lookupValue: "Va",
      lookupDisplayName: "VA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244394",
    },
  ],
  CurrentUse: [
    {
      lookupValue: "Agricultural",
      lookupDisplayName: "Agricultural",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244464",
    },
    {
      lookupValue: "Automotive",
      lookupDisplayName: "Automotive",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Automotive",
    },
    {
      lookupValue: "Cattle",
      lookupDisplayName: "Cattle",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cattle",
    },
    {
      lookupValue: "Commercial",
      lookupDisplayName: "Commercial",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244467",
    },
    {
      lookupValue: "Dairy",
      lookupDisplayName: "Dairy",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dairy",
    },
    {
      lookupValue: "Farm",
      lookupDisplayName: "Farm",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244469",
    },
    {
      lookupValue: "Fishery",
      lookupDisplayName: "Fishery",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fishery",
    },
    {
      lookupValue: "Grazing",
      lookupDisplayName: "Grazing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Grazing",
    },
    {
      lookupValue: "HighwayTouristService",
      lookupDisplayName: "Highway/Tourist Service",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244472",
    },
    {
      lookupValue: "Horses",
      lookupDisplayName: "Horses",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Horses",
    },
    {
      lookupValue: "Hunting",
      lookupDisplayName: "Hunting",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hunting",
    },
    {
      lookupValue: "Industrial",
      lookupDisplayName: "Industrial",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244475",
    },
    {
      lookupValue: "Investment",
      lookupDisplayName: "Investment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Investment",
    },
    {
      lookupValue: "Livestock",
      lookupDisplayName: "Livestock",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Livestock",
    },
    {
      lookupValue: "ManufacturedHome",
      lookupDisplayName: "Manufactured Home",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244478",
    },
    {
      lookupValue: "MedicalDental",
      lookupDisplayName: "Medical/Dental",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244479",
    },
    {
      lookupValue: "MiniStorage",
      lookupDisplayName: "Mini-Storage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mini-Storage",
    },
    {
      lookupValue: "MixedUse",
      lookupDisplayName: "Mixed Use",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244481",
    },
    {
      lookupValue: "MultiFamily",
      lookupDisplayName: "Multi-Family",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244482",
    },
    {
      lookupValue: "Nursery",
      lookupDisplayName: "Nursery",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244483",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244484",
    },
    {
      lookupValue: "Orchard",
      lookupDisplayName: "Orchard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Orchard",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244486",
    },
    {
      lookupValue: "Pasture",
      lookupDisplayName: "Pasture",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pasture",
    },
    {
      lookupValue: "PlaceOfWorship",
      lookupDisplayName: "Place of Worship",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Place+of+Worship",
    },
    {
      lookupValue: "Plantable",
      lookupDisplayName: "Plantable",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Plantable",
    },
    {
      lookupValue: "Poultry",
      lookupDisplayName: "Poultry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Poultry",
    },
    {
      lookupValue: "Ranch",
      lookupDisplayName: "Ranch",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244491",
    },
    {
      lookupValue: "Recreational",
      lookupDisplayName: "Recreational",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Recreational",
    },
    {
      lookupValue: "Residential",
      lookupDisplayName: "Residential",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244493",
    },
    {
      lookupValue: "Retail",
      lookupDisplayName: "Retail",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244494",
    },
    {
      lookupValue: "RowCrops",
      lookupDisplayName: "Row Crops",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Row+Crops",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244496",
    },
    {
      lookupValue: "SingleFamily",
      lookupDisplayName: "Single Family",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244497",
    },
    {
      lookupValue: "Subdivision",
      lookupDisplayName: "Subdivision",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Subdivision",
    },
    {
      lookupValue: "Timber",
      lookupDisplayName: "Timber",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Timber",
    },
    {
      lookupValue: "TreeFarm",
      lookupDisplayName: "Tree Farm",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tree+Farm",
    },
    {
      lookupValue: "Unimproved",
      lookupDisplayName: "Unimproved",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244501",
    },
    {
      lookupValue: "Vacant",
      lookupDisplayName: "Vacant",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244502",
    },
    {
      lookupValue: "Vineyard",
      lookupDisplayName: "Vineyard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vineyard",
    },
    {
      lookupValue: "Warehouse",
      lookupDisplayName: "Warehouse",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244504",
    },
  ],
  DailySchedule: [
    {
      lookupValue: "FridayAM",
      lookupDisplayName: "Friday AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Friday+AM",
    },
    {
      lookupValue: "FridayPM",
      lookupDisplayName: "Friday PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Friday+PM",
    },
    {
      lookupValue: "MondayAM",
      lookupDisplayName: "Monday AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Monday+AM",
    },
    {
      lookupValue: "MondayPM",
      lookupDisplayName: "Monday PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Monday+PM",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244582",
    },
    {
      lookupValue: "SaturdayAM",
      lookupDisplayName: "Saturday AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Saturday+AM",
    },
    {
      lookupValue: "SaturdayPM",
      lookupDisplayName: "Saturday PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Saturday+PM",
    },
    {
      lookupValue: "SundayAM",
      lookupDisplayName: "Sunday AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sunday+AM",
    },
    {
      lookupValue: "SundayPM",
      lookupDisplayName: "Sunday PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sunday+PM",
    },
    {
      lookupValue: "ThursdayAM",
      lookupDisplayName: "Thursday AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Thursday+AM",
    },
    {
      lookupValue: "ThursdayPM",
      lookupDisplayName: "Thursday PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Thursday+PM",
    },
    {
      lookupValue: "TuesdayAM",
      lookupDisplayName: "Tuesday AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tuesday+AM",
    },
    {
      lookupValue: "TuesdayPM",
      lookupDisplayName: "Tuesday PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tuesday+PM",
    },
    {
      lookupValue: "WednesdayAM",
      lookupDisplayName: "Wednesday AM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wednesday+AM",
    },
    {
      lookupValue: "WednesdayPM",
      lookupDisplayName: "Wednesday PM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wednesday+PM",
    },
  ],
  DevelopmentStatus: [
    {
      lookupValue: "Completed",
      lookupDisplayName: "Completed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Completed",
    },
    {
      lookupValue: "FinishedLots",
      lookupDisplayName: "Finished Lot(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244568",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244569",
    },
    {
      lookupValue: "Proposed",
      lookupDisplayName: "Proposed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Proposed",
    },
    {
      lookupValue: "RawLand",
      lookupDisplayName: "Raw Land",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Raw+Land",
    },
    {
      lookupValue: "RoughGrade",
      lookupDisplayName: "Rough Grade",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rough+Grade",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244573",
    },
    {
      lookupValue: "SitePlanApproved",
      lookupDisplayName: "Site Plan Approved",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Site+Plan+Approved",
    },
    {
      lookupValue: "SitePlanFiled",
      lookupDisplayName: "Site Plan Filed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Site+Plan+Filed",
    },
    {
      lookupValue: "UnderConstruction",
      lookupDisplayName: "Under Construction",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244576",
    },
  ],
  DeviceType: [
    {
      lookupValue: "Desktop",
      lookupDisplayName: "Desktop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Desktop",
    },
    {
      lookupValue: "Mobile",
      lookupDisplayName: "Mobile",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244595",
    },
    {
      lookupValue: "Tablet",
      lookupDisplayName: "Tablet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tablet",
    },
    {
      lookupValue: "Unknown",
      lookupDisplayName: "Unknown",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244597",
    },
    {
      lookupValue: "Wearable",
      lookupDisplayName: "Wearable",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wearable",
    },
  ],
  DirectionFaces: [
    {
      lookupValue: "East",
      lookupDisplayName: "East",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/East",
    },
    {
      lookupValue: "North",
      lookupDisplayName: "North",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/North",
    },
    {
      lookupValue: "Northeast",
      lookupDisplayName: "Northeast",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Northeast",
    },
    {
      lookupValue: "Northwest",
      lookupDisplayName: "Northwest",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Northwest",
    },
    {
      lookupValue: "South",
      lookupDisplayName: "South",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/South",
    },
    {
      lookupValue: "Southeast",
      lookupDisplayName: "Southeast",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Southeast",
    },
    {
      lookupValue: "Southwest",
      lookupDisplayName: "Southwest",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Southwest",
    },
    {
      lookupValue: "West",
      lookupDisplayName: "West",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/West",
    },
  ],
  DoorFeatures: [
    {
      lookupValue: "EnergyStarQualifiedDoors",
      lookupDisplayName: "ENERGY STAR Qualified Doors",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Doors",
    },
    {
      lookupValue: "FrenchDoors",
      lookupDisplayName: "French Doors",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/French+Doors",
    },
    {
      lookupValue: "MirroredClosetDoors",
      lookupDisplayName: "Mirrored Closet Door(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244602",
    },
    {
      lookupValue: "SlidingDoors",
      lookupDisplayName: "Sliding Doors",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sliding+Doors",
    },
    {
      lookupValue: "StormDoors",
      lookupDisplayName: "Storm Door(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244604",
    },
  ],
  Electric: [
    {
      lookupValue: "Amps100",
      lookupDisplayName: "100 Amp Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/100+Amp+Service",
    },
    {
      lookupValue: "Amps150",
      lookupDisplayName: "150 Amp Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/150+Amp+Service",
    },
    {
      lookupValue: "Amps200OrMore",
      lookupDisplayName: "200+ Amp Service",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244609",
    },
    {
      lookupValue: "CircuitBreakers",
      lookupDisplayName: "Circuit Breakers",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Circuit+Breakers",
    },
    {
      lookupValue: "EnergyStorageDevice",
      lookupDisplayName: "Energy Storage Device",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Energy+Storage+Device",
    },
    {
      lookupValue: "Fuses",
      lookupDisplayName: "Fuses",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fuses",
    },
    {
      lookupValue: "Generator",
      lookupDisplayName: "Generator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Generator",
    },
    {
      lookupValue: "NetMeter",
      lookupDisplayName: "Net Meter",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Net+Meter",
    },
    {
      lookupValue: "PhotovoltaicsSellerOwned",
      lookupDisplayName: "Photovoltaics Seller Owned",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Photovoltaics+Seller+Owned",
    },
    {
      lookupValue: "PhotovoltaicsThirdPartyOwned",
      lookupDisplayName: "Photovoltaics Third-Party Owned",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Photovoltaics+Third-Party+Owned",
    },
    {
      lookupValue: "PreWiredForRenewables",
      lookupDisplayName: "Pre-Wired for Renewables",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Pre-Wired+for+Renewables",
    },
    {
      lookupValue: "ReadyForRenewables",
      lookupDisplayName: "Ready for Renewables",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ready+for+Renewables",
    },
    {
      lookupValue: "Underground",
      lookupDisplayName: "Underground",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Underground",
    },
    {
      lookupValue: "Volts220",
      lookupDisplayName: "220 Volts",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/220+Volts",
    },
    {
      lookupValue: "Volts220ForSpa",
      lookupDisplayName: "220 Volts For Spa",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/220+Volts+For+Spa",
    },
    {
      lookupValue: "Volts220InGarage",
      lookupDisplayName: "220 Volts in Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/220+Volts+in+Garage",
    },
    {
      lookupValue: "Volts220InKitchen",
      lookupDisplayName: "220 Volts in Kitchen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/220+Volts+in+Kitchen",
    },
    {
      lookupValue: "Volts220InLanudry",
      lookupDisplayName: "220 Volts in Laundry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/220+Volts+in+Laundry",
    },
    {
      lookupValue: "Volts220InWorkshop",
      lookupDisplayName: "220 Volts in Workshop",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/220+Volts+in+Workshop",
    },
    {
      lookupValue: "Volts440",
      lookupDisplayName: "440 Volts",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/440+Volts",
    },
    {
      lookupValue: "WindTurbineSellerOwned",
      lookupDisplayName: "Wind Turbine Seller Owned",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Wind+Turbine+Seller+Owned",
    },
    {
      lookupValue: "WindTurbineThirdPartyOwned",
      lookupDisplayName: "Wind Turbine Third-Party Owned",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Wind+Turbine+Third-Party+Owned",
    },
  ],
  EventTarget: [
    {
      lookupValue: "Agent",
      lookupDisplayName: "Agent",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244640",
    },
    {
      lookupValue: "Broker",
      lookupDisplayName: "Broker",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244641",
    },
    {
      lookupValue: "Digg",
      lookupDisplayName: "Digg",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244642",
    },
    {
      lookupValue: "Email",
      lookupDisplayName: "Email",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244643",
    },
    {
      lookupValue: "Facebook",
      lookupDisplayName: "Facebook",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244644",
    },
    {
      lookupValue: "FacebookMessenger",
      lookupDisplayName: "Facebook Messenger",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244645",
    },
    {
      lookupValue: "Googleplus",
      lookupDisplayName: "GooglePlus",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244646",
    },
    {
      lookupValue: "Imessage",
      lookupDisplayName: "iMessage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244647",
    },
    {
      lookupValue: "Instagram",
      lookupDisplayName: "Instagram",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244648",
    },
    {
      lookupValue: "Linkedin",
      lookupDisplayName: "LinkedIn",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244649",
    },
    {
      lookupValue: "Pinterest",
      lookupDisplayName: "Pinterest",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244650",
    },
    {
      lookupValue: "Reddit",
      lookupDisplayName: "Reddit",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244651",
    },
    {
      lookupValue: "Slack",
      lookupDisplayName: "Slack",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244652",
    },
    {
      lookupValue: "Sms",
      lookupDisplayName: "SMS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244653",
    },
    {
      lookupValue: "Snapchat",
      lookupDisplayName: "Snapchat",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244654",
    },
    {
      lookupValue: "Stumbleupon",
      lookupDisplayName: "StumbleUpon",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244655",
    },
    {
      lookupValue: "Tumblr",
      lookupDisplayName: "Tumblr",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244656",
    },
    {
      lookupValue: "Twitter",
      lookupDisplayName: "Twitter",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244657",
    },
    {
      lookupValue: "Youtube",
      lookupDisplayName: "YouTube",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244658",
    },
  ],
  EventType: [
    {
      lookupValue: "ClickedOnEmailAddress",
      lookupDisplayName: "Clicked on Email Address",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Clicked+on+Email+Address",
    },
    {
      lookupValue: "ClickedOnPhoneNumber",
      lookupDisplayName: "Clicked on Phone Number",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Clicked+on+Phone+Number",
    },
    {
      lookupValue: "ClickToPrimaryHostedSite",
      lookupDisplayName: "Click to Primary Hosted Site",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Click+to+Primary+Hosted+Site",
    },
    {
      lookupValue: "Comments",
      lookupDisplayName: "Comments",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Comments",
    },
    {
      lookupValue: "DetailedView",
      lookupDisplayName: "Detailed View",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Detailed+View",
    },
    {
      lookupValue: "Discard",
      lookupDisplayName: "Discard",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244665",
    },
    {
      lookupValue: "DrivingDirections",
      lookupDisplayName: "Driving Directions",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Driving+Directions",
    },
    {
      lookupValue: "ExitDetailedView",
      lookupDisplayName: "Exit Detailed View",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Exit+Detailed+View",
    },
    {
      lookupValue: "Favorited",
      lookupDisplayName: "Favorited",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Favorited",
    },
    {
      lookupValue: "Maybe",
      lookupDisplayName: "Maybe",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Maybe",
    },
    {
      lookupValue: "NonDetailedView",
      lookupDisplayName: "Non-Detailed View",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Non-Detailed+View",
    },
    {
      lookupValue: "ObjectModified",
      lookupDisplayName: "Object Modified",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Object+Modified",
    },
    {
      lookupValue: "PhotoGallery",
      lookupDisplayName: "Photo Gallery",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Photo+Gallery",
    },
    {
      lookupValue: "Printed",
      lookupDisplayName: "Printed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Printed",
    },
    {
      lookupValue: "PropertyVideos",
      lookupDisplayName: "Property Videos",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Property+Videos",
    },
    {
      lookupValue: "Search",
      lookupDisplayName: "Search",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Search",
    },
    {
      lookupValue: "Share",
      lookupDisplayName: "Share",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Share",
    },
    {
      lookupValue: "SubmissionOfLeadForm",
      lookupDisplayName: "Submission of Lead Form",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Submission+of+Lead+Form",
    },
    {
      lookupValue: "VirtualTour",
      lookupDisplayName: "Virtual Tour",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Virtual+Tour",
    },
  ],
  ExistingLeaseType: [
    {
      lookupValue: "AbsoluteNet",
      lookupDisplayName: "Absolute Net",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Absolute+Net",
    },
    {
      lookupValue: "CpiAdjustment",
      lookupDisplayName: "CPI Adjustment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CPI+Adjustment",
    },
    {
      lookupValue: "EscalationClause",
      lookupDisplayName: "Escalation Clause",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Escalation+Clause",
    },
    {
      lookupValue: "Gross",
      lookupDisplayName: "Gross",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gross",
    },
    {
      lookupValue: "GroundLease",
      lookupDisplayName: "Ground Lease",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ground+Lease",
    },
    {
      lookupValue: "Net",
      lookupDisplayName: "Net",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Net",
    },
    {
      lookupValue: "Nn",
      lookupDisplayName: "NN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NN",
    },
    {
      lookupValue: "Nnn",
      lookupDisplayName: "NNN",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NNN",
    },
    {
      lookupValue: "Oral",
      lookupDisplayName: "Oral",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Oral",
    },
  ],
  ExteriorFeatures: [
    {
      lookupValue: "Awnings",
      lookupDisplayName: "Awning(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244680",
    },
    {
      lookupValue: "Balcony",
      lookupDisplayName: "Balcony",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244681",
    },
    {
      lookupValue: "Barbecue",
      lookupDisplayName: "Barbecue",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244682",
    },
    {
      lookupValue: "BasketballCourt",
      lookupDisplayName: "Basketball Court",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244683",
    },
    {
      lookupValue: "BoatSlip",
      lookupDisplayName: "Boat Slip",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244684",
    },
    {
      lookupValue: "BuiltInBarbecue",
      lookupDisplayName: "Built-in Barbecue",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Built-in+Barbecue",
    },
    {
      lookupValue: "Courtyard",
      lookupDisplayName: "Courtyard",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244686",
    },
    {
      lookupValue: "CoveredCourtyard",
      lookupDisplayName: "Covered Courtyard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Covered+Courtyard",
    },
    {
      lookupValue: "Dock",
      lookupDisplayName: "Dock",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244688",
    },
    {
      lookupValue: "DogRun",
      lookupDisplayName: "Dog Run",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dog+Run",
    },
    {
      lookupValue: "ElectricGrill",
      lookupDisplayName: "Electric Grill",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electric+Grill",
    },
    {
      lookupValue: "FirePit",
      lookupDisplayName: "Fire Pit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fire+Pit",
    },
    {
      lookupValue: "Garden",
      lookupDisplayName: "Garden",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244692",
    },
    {
      lookupValue: "GasGrill",
      lookupDisplayName: "Gas Grill",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Grill",
    },
    {
      lookupValue: "GrayWaterSystem",
      lookupDisplayName: "Gray Water System",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244694",
    },
    {
      lookupValue: "Kennel",
      lookupDisplayName: "Kennel",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kennel",
    },
    {
      lookupValue: "Lighting",
      lookupDisplayName: "Lighting",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244696",
    },
    {
      lookupValue: "MistingSystem",
      lookupDisplayName: "Misting System",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Misting+System",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244698",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244699",
    },
    {
      lookupValue: "OutdoorGrill",
      lookupDisplayName: "Outdoor Grill",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Outdoor+Grill",
    },
    {
      lookupValue: "OutdoorKitchen",
      lookupDisplayName: "Outdoor Kitchen",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244701",
    },
    {
      lookupValue: "OutdoorShower",
      lookupDisplayName: "Outdoor Shower",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Outdoor+Shower",
    },
    {
      lookupValue: "PermeablePaving",
      lookupDisplayName: "Permeable Paving",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Permeable+Paving",
    },
    {
      lookupValue: "Playground",
      lookupDisplayName: "Playground",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244704",
    },
    {
      lookupValue: "PrivateEntrance",
      lookupDisplayName: "Private Entrance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Private+Entrance",
    },
    {
      lookupValue: "PrivateYard",
      lookupDisplayName: "Private Yard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Private+Yard",
    },
    {
      lookupValue: "RainBarrelCisterns",
      lookupDisplayName: "Rain Barrel/Cistern(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244707",
    },
    {
      lookupValue: "RainGutters",
      lookupDisplayName: "Rain Gutters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rain+Gutters",
    },
    {
      lookupValue: "RvHookup",
      lookupDisplayName: "RV Hookup",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RV+Hookup",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244710",
    },
    {
      lookupValue: "TennisCourts",
      lookupDisplayName: "Tennis Court(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244711",
    },
    {
      lookupValue: "UncoveredCourtyard",
      lookupDisplayName: "Uncovered Courtyard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Uncovered+Courtyard",
    },
  ],
  FeeFrequency: [
    {
      lookupValue: "Annually",
      lookupDisplayName: "Annually",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Annually",
    },
    {
      lookupValue: "BiMonthly",
      lookupDisplayName: "Bi-Monthly",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bi-Monthly",
    },
    {
      lookupValue: "BiWeekly",
      lookupDisplayName: "Bi-Weekly",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bi-Weekly",
    },
    {
      lookupValue: "Daily",
      lookupDisplayName: "Daily",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Daily",
    },
    {
      lookupValue: "Monthly",
      lookupDisplayName: "Monthly",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Monthly",
    },
    {
      lookupValue: "OneTime",
      lookupDisplayName: "One Time",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/One+Time",
    },
    {
      lookupValue: "Quarterly",
      lookupDisplayName: "Quarterly",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Quarterly",
    },
    {
      lookupValue: "Seasonal",
      lookupDisplayName: "Seasonal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Seasonal",
    },
    {
      lookupValue: "SemiAnnually",
      lookupDisplayName: "Semi-Annually",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Semi-Annually",
    },
    {
      lookupValue: "SemiMonthly",
      lookupDisplayName: "Semi-Monthly",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Semi-Monthly",
    },
    {
      lookupValue: "Weekly",
      lookupDisplayName: "Weekly",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Weekly",
    },
  ],
  Fencing: [
    {
      lookupValue: "BackYard",
      lookupDisplayName: "Back Yard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Back+Yard",
    },
    {
      lookupValue: "BarbedWire",
      lookupDisplayName: "Barbed Wire",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Barbed+Wire",
    },
    {
      lookupValue: "Block",
      lookupDisplayName: "Block",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244752",
    },
    {
      lookupValue: "Brick",
      lookupDisplayName: "Brick",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244753",
    },
    {
      lookupValue: "ChainLink",
      lookupDisplayName: "Chain Link",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chain+Link",
    },
    {
      lookupValue: "CrossFenced",
      lookupDisplayName: "Cross Fenced",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cross+Fenced",
    },
    {
      lookupValue: "Electric",
      lookupDisplayName: "Electric",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electric",
    },
    {
      lookupValue: "Fenced",
      lookupDisplayName: "Fenced",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fenced",
    },
    {
      lookupValue: "FrontYard",
      lookupDisplayName: "Front Yard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Front+Yard",
    },
    {
      lookupValue: "Full",
      lookupDisplayName: "Full",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Full",
    },
    {
      lookupValue: "Gate",
      lookupDisplayName: "Gate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gate",
    },
    {
      lookupValue: "Invisible",
      lookupDisplayName: "Invisible",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Invisible",
    },
    {
      lookupValue: "Masonry",
      lookupDisplayName: "Masonry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Masonry",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244763",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244764",
    },
    {
      lookupValue: "Partial",
      lookupDisplayName: "Partial",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Partial",
    },
    {
      lookupValue: "PartialCross",
      lookupDisplayName: "Partial Cross",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Partial+Cross",
    },
    {
      lookupValue: "Perimeter",
      lookupDisplayName: "Perimeter",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Perimeter",
    },
    {
      lookupValue: "Pipe",
      lookupDisplayName: "Pipe",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pipe",
    },
    {
      lookupValue: "Privacy",
      lookupDisplayName: "Privacy",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Privacy",
    },
    {
      lookupValue: "Security",
      lookupDisplayName: "Security",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244770",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244771",
    },
    {
      lookupValue: "SlumpStone",
      lookupDisplayName: "Slump Stone",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Slump+Stone",
    },
    {
      lookupValue: "SplitRail",
      lookupDisplayName: "Split Rail",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Split+Rail",
    },
    {
      lookupValue: "Stone",
      lookupDisplayName: "Stone",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244774",
    },
    {
      lookupValue: "Vinyl",
      lookupDisplayName: "Vinyl",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244775",
    },
    {
      lookupValue: "Wire",
      lookupDisplayName: "Wire",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wire",
    },
    {
      lookupValue: "Wood",
      lookupDisplayName: "Wood",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244777",
    },
    {
      lookupValue: "WroughtIron",
      lookupDisplayName: "Wrought Iron",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wrought+Iron",
    },
  ],
  FinancialDataSource: [
    {
      lookupValue: "Accountant",
      lookupDisplayName: "Accountant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Accountant",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244728",
    },
    {
      lookupValue: "PropertyManager",
      lookupDisplayName: "Property Manager",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Property+Manager",
    },
  ],
  FireplaceFeatures: [
    {
      lookupValue: "Basement",
      lookupDisplayName: "Basement",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244790",
    },
    {
      lookupValue: "Bath",
      lookupDisplayName: "Bath",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bath",
    },
    {
      lookupValue: "Bedroom",
      lookupDisplayName: "Bedroom",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244792",
    },
    {
      lookupValue: "BlowerFan",
      lookupDisplayName: "Blower Fan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Blower+Fan",
    },
    {
      lookupValue: "Circulating",
      lookupDisplayName: "Circulating",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Circulating",
    },
    {
      lookupValue: "Decorative",
      lookupDisplayName: "Decorative",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Decorative",
    },
    {
      lookupValue: "Den",
      lookupDisplayName: "Den",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244796",
    },
    {
      lookupValue: "DiningRoom",
      lookupDisplayName: "Dining Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244797",
    },
    {
      lookupValue: "DoubleSided",
      lookupDisplayName: "Double Sided",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Double+Sided",
    },
    {
      lookupValue: "Electric",
      lookupDisplayName: "Electric",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244799",
    },
    {
      lookupValue: "EpaCertifiedWoodStove",
      lookupDisplayName: "EPA Certified Wood Stove",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/EPA+Certified+Wood+Stove",
    },
    {
      lookupValue: "EpaQualifiedFireplace",
      lookupDisplayName: "EPA Qualified Fireplace",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/EPA+Qualified+Fireplace",
    },
    {
      lookupValue: "FactoryBuilt",
      lookupDisplayName: "Factory Built",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Factory+Built",
    },
    {
      lookupValue: "FamilyRoom",
      lookupDisplayName: "Family Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244803",
    },
    {
      lookupValue: "FirePit",
      lookupDisplayName: "Fire Pit",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244804",
    },
    {
      lookupValue: "FreeStanding",
      lookupDisplayName: "Free Standing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Free+Standing",
    },
    {
      lookupValue: "Gas",
      lookupDisplayName: "Gas",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244806",
    },
    {
      lookupValue: "GasLog",
      lookupDisplayName: "Gas Log",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Log",
    },
    {
      lookupValue: "GasStarter",
      lookupDisplayName: "Gas Starter",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Starter",
    },
    {
      lookupValue: "GlassDoors",
      lookupDisplayName: "Glass Doors",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Glass+Doors",
    },
    {
      lookupValue: "GreatRoom",
      lookupDisplayName: "Great Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244810",
    },
    {
      lookupValue: "Heatilator",
      lookupDisplayName: "Heatilator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Heatilator",
    },
    {
      lookupValue: "Insert",
      lookupDisplayName: "Insert",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Insert",
    },
    {
      lookupValue: "Kitchen",
      lookupDisplayName: "Kitchen",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244813",
    },
    {
      lookupValue: "Library",
      lookupDisplayName: "Library",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244814",
    },
    {
      lookupValue: "LivingRoom",
      lookupDisplayName: "Living Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244815",
    },
    {
      lookupValue: "Masonry",
      lookupDisplayName: "Masonry",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244816",
    },
    {
      lookupValue: "MasterBedroom",
      lookupDisplayName: "Master Bedroom",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244817",
    },
    {
      lookupValue: "Metal",
      lookupDisplayName: "Metal",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244818",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244819",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244820",
    },
    {
      lookupValue: "Outside",
      lookupDisplayName: "Outside",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Outside",
    },
    {
      lookupValue: "PelletStove",
      lookupDisplayName: "Pellet Stove",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pellet+Stove",
    },
    {
      lookupValue: "Propane",
      lookupDisplayName: "Propane",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244823",
    },
    {
      lookupValue: "RaisedHearth",
      lookupDisplayName: "Raised Hearth",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Raised+Hearth",
    },
    {
      lookupValue: "RecreationRoom",
      lookupDisplayName: "Recreation Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244825",
    },
    {
      lookupValue: "SealedCombustion",
      lookupDisplayName: "Sealed Combustion",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sealed+Combustion",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244827",
    },
    {
      lookupValue: "SeeThrough",
      lookupDisplayName: "See Through",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/See+Through",
    },
    {
      lookupValue: "Stone",
      lookupDisplayName: "Stone",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244829",
    },
    {
      lookupValue: "Ventless",
      lookupDisplayName: "Ventless",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ventless",
    },
    {
      lookupValue: "WoodBurning",
      lookupDisplayName: "Wood Burning",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wood+Burning",
    },
    {
      lookupValue: "WoodBurningStove",
      lookupDisplayName: "Wood Burning Stove",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wood+Burning+Stove",
    },
    {
      lookupValue: "ZeroClearance",
      lookupDisplayName: "Zero Clearance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Zero+Clearance",
    },
  ],
  Flooring: [
    {
      lookupValue: "Adobe",
      lookupDisplayName: "Adobe",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244835",
    },
    {
      lookupValue: "Bamboo",
      lookupDisplayName: "Bamboo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bamboo",
    },
    {
      lookupValue: "Brick",
      lookupDisplayName: "Brick",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244837",
    },
    {
      lookupValue: "Carpet",
      lookupDisplayName: "Carpet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Carpet",
    },
    {
      lookupValue: "CeramicTile",
      lookupDisplayName: "Ceramic Tile",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ceramic+Tile",
    },
    {
      lookupValue: "Clay",
      lookupDisplayName: "Clay",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Clay",
    },
    {
      lookupValue: "Combination",
      lookupDisplayName: "Combination",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244841",
    },
    {
      lookupValue: "Concrete",
      lookupDisplayName: "Concrete",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244842",
    },
    {
      lookupValue: "Cork",
      lookupDisplayName: "Cork",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cork",
    },
    {
      lookupValue: "CriGreenLabelPlusCertifiedCarpet",
      lookupDisplayName: "CRI Green Label Plus Certified Carpet",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/CRI+Green+Label+Plus+Certified+Carpet",
    },
    {
      lookupValue: "Dirt",
      lookupDisplayName: "Dirt",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244845",
    },
    {
      lookupValue: "FloorscoreCertifiedFlooring",
      lookupDisplayName: "FloorScore(r) Certified Flooring",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/FloorScore%28r%29+Certified+Flooring",
    },
    {
      lookupValue: "FscOrSfiCertifiedSourceHardwood",
      lookupDisplayName: "FSC or SFI Certified Source Hardwood",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/FSC+or+SFI+Certified+Source+Hardwood",
    },
    {
      lookupValue: "Granite",
      lookupDisplayName: "Granite",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Granite",
    },
    {
      lookupValue: "Hardwood",
      lookupDisplayName: "Hardwood",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hardwood",
    },
    {
      lookupValue: "Laminate",
      lookupDisplayName: "Laminate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Laminate",
    },
    {
      lookupValue: "Linoleum",
      lookupDisplayName: "Linoleum",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Linoleum",
    },
    {
      lookupValue: "Marble",
      lookupDisplayName: "Marble",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Marble",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244853",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244854",
    },
    {
      lookupValue: "PaintedStained",
      lookupDisplayName: "Painted/Stained",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244855",
    },
    {
      lookupValue: "Parquet",
      lookupDisplayName: "Parquet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Parquet",
    },
    {
      lookupValue: "Pavers",
      lookupDisplayName: "Pavers",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pavers",
    },
    {
      lookupValue: "ReclaimedWood",
      lookupDisplayName: "Reclaimed Wood",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Reclaimed+Wood",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244859",
    },
    {
      lookupValue: "SimulatedWood",
      lookupDisplayName: "Simulated Wood",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Simulated+Wood",
    },
    {
      lookupValue: "Slate",
      lookupDisplayName: "Slate",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244861",
    },
    {
      lookupValue: "Softwood",
      lookupDisplayName: "Softwood",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Softwood",
    },
    {
      lookupValue: "Stamped",
      lookupDisplayName: "Stamped",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stamped",
    },
    {
      lookupValue: "Stone",
      lookupDisplayName: "Stone",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244864",
    },
    {
      lookupValue: "Sustainable",
      lookupDisplayName: "Sustainable",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sustainable",
    },
    {
      lookupValue: "Terrazzo",
      lookupDisplayName: "Terrazzo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Terrazzo",
    },
    {
      lookupValue: "Tile",
      lookupDisplayName: "Tile",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244867",
    },
    {
      lookupValue: "Varies",
      lookupDisplayName: "Varies",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Varies",
    },
    {
      lookupValue: "Vinyl",
      lookupDisplayName: "Vinyl",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244869",
    },
    {
      lookupValue: "Wood",
      lookupDisplayName: "Wood",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244870",
    },
  ],
  FoundationDetails: [
    {
      lookupValue: "Block",
      lookupDisplayName: "Block",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244731",
    },
    {
      lookupValue: "BrickMortar",
      lookupDisplayName: "Brick/Mortar",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244732",
    },
    {
      lookupValue: "Combination",
      lookupDisplayName: "Combination",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Combination",
    },
    {
      lookupValue: "ConcretePerimeter",
      lookupDisplayName: "Concrete Perimeter",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Concrete+Perimeter",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244735",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244742",
    },
    {
      lookupValue: "Permanent",
      lookupDisplayName: "Permanent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Permanent",
    },
    {
      lookupValue: "PillarPostPier",
      lookupDisplayName: "Pillar/Post/Pier",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244737",
    },
    {
      lookupValue: "Raised",
      lookupDisplayName: "Raised",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Raised",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244739",
    },
    {
      lookupValue: "Slab",
      lookupDisplayName: "Slab",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Slab",
    },
    {
      lookupValue: "Stone",
      lookupDisplayName: "Stone",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244741",
    },
  ],
  FrontageType: [
    {
      lookupValue: "BayHarbor",
      lookupDisplayName: "Bay/Harbor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244780",
    },
    {
      lookupValue: "GolfCourse",
      lookupDisplayName: "Golf Course",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244781",
    },
    {
      lookupValue: "LagoonEstuary",
      lookupDisplayName: "Lagoon/Estuary",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244782",
    },
    {
      lookupValue: "Lakefront",
      lookupDisplayName: "Lakefront",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lakefront",
    },
    {
      lookupValue: "Oceanfront",
      lookupDisplayName: "Oceanfront",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Oceanfront",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244785",
    },
    {
      lookupValue: "River",
      lookupDisplayName: "River",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/River",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244787",
    },
    {
      lookupValue: "Waterfront",
      lookupDisplayName: "Waterfront",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244788",
    },
  ],
  Furnished: [
    {
      lookupValue: "Furnished",
      lookupDisplayName: "Furnished",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Furnished",
    },
    {
      lookupValue: "Negotiable",
      lookupDisplayName: "Negotiable",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244748",
    },
    {
      lookupValue: "Partially",
      lookupDisplayName: "Partially",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Partially",
    },
    {
      lookupValue: "Unfurnished",
      lookupDisplayName: "Unfurnished",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unfurnished",
    },
  ],
  GreenBuildingVerificationType: [
    {
      lookupValue: "CertifiedPassiveHouse",
      lookupDisplayName: "Certified Passive House",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Certified+Passive+House",
    },
    {
      lookupValue: "EnergyStarCertifiedHomes",
      lookupDisplayName: "ENERGY STAR Certified Homes",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Certified+Homes",
    },
    {
      lookupValue: "Enerphit",
      lookupDisplayName: "EnerPHit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/EnerPHit",
    },
    {
      lookupValue: "HersIndexScore",
      lookupDisplayName: "HERS Index Score",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HERS+Index+Score",
    },
    {
      lookupValue: "HomeEnergyScore",
      lookupDisplayName: "Home Energy Score",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Home+Energy+Score",
    },
    {
      lookupValue: "HomeEnergyUpgradeCertificateOfEnergyEfficiencyImprovements",
      lookupDisplayName:
        "Home Energy Upgrade Certificate of Energy Efficiency Improvements",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Home+Energy+Upgrade+Certificate+of+Energy+Efficiency+Improvements",
    },
    {
      lookupValue: "HomeEnergyUpgradeCertificateOfEnergyEfficiencyPerformance",
      lookupDisplayName:
        "Home Energy Upgrade Certificate of Energy Efficiency Performance",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Home+Energy+Upgrade+Certificate+of+Energy+Efficiency+Performance",
    },
    {
      lookupValue: "HomePerformanceWithEnergyStar",
      lookupDisplayName: "Home Performance with ENERGY STAR",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Home+Performance+with+ENERGY+STAR",
    },
    {
      lookupValue: "IndoorAirplus",
      lookupDisplayName: "Indoor airPLUS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Indoor+airPLUS",
    },
    {
      lookupValue: "LeedForHomes",
      lookupDisplayName: "LEED For Homes",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LEED+For+Homes",
    },
    {
      lookupValue: "LivingBuildingChallenge",
      lookupDisplayName: "Living Building Challenge",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Living+Building+Challenge",
    },
    {
      lookupValue: "NgbsNewConstruction",
      lookupDisplayName: "NGBS New Construction",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/NGBS+New+Construction",
    },
    {
      lookupValue: "NgbsSmallProjectsRemodel",
      lookupDisplayName: "NGBS Small Projects Remodel",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/NGBS+Small+Projects+Remodel",
    },
    {
      lookupValue: "NgbsWholeHomeRemodel",
      lookupDisplayName: "NGBS Whole-Home Remodel",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/NGBS+Whole-Home+Remodel",
    },
    {
      lookupValue: "PhiusPlus",
      lookupDisplayName: "PHIUS+",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244887",
    },
    {
      lookupValue: "Watersense",
      lookupDisplayName: "WaterSense",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/WaterSense",
    },
    {
      lookupValue: "ZeroEnergyReadyHome",
      lookupDisplayName: "Zero Energy Ready Home",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Zero+Energy+Ready+Home",
    },
  ],
  GreenEnergyEfficient: [
    {
      lookupValue: "Appliances",
      lookupDisplayName: "Appliances",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244891",
    },
    {
      lookupValue: "Construction",
      lookupDisplayName: "Construction",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Construction",
    },
    {
      lookupValue: "Doors",
      lookupDisplayName: "Doors",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Doors",
    },
    {
      lookupValue: "ExposureShade",
      lookupDisplayName: "Exposure/Shade",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244894",
    },
    {
      lookupValue: "Hvac",
      lookupDisplayName: "HVAC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HVAC",
    },
    {
      lookupValue: "Incentives",
      lookupDisplayName: "Incentives",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Incentives",
    },
    {
      lookupValue: "Insulation",
      lookupDisplayName: "Insulation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Insulation",
    },
    {
      lookupValue: "Lighting",
      lookupDisplayName: "Lighting",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lighting",
    },
    {
      lookupValue: "Roof",
      lookupDisplayName: "Roof",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Roof",
    },
    {
      lookupValue: "Thermostat",
      lookupDisplayName: "Thermostat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Thermostat",
    },
    {
      lookupValue: "WaterHeater",
      lookupDisplayName: "Water Heater",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Heater",
    },
    {
      lookupValue: "Windows",
      lookupDisplayName: "Windows",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Windows",
    },
  ],
  GreenEnergyGeneration: [
    {
      lookupValue: "Solar",
      lookupDisplayName: "Solar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Solar",
    },
    {
      lookupValue: "Wind",
      lookupDisplayName: "Wind",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wind",
    },
  ],
  GreenIndoorAirQuality: [
    {
      lookupValue: "ContaminantControl",
      lookupDisplayName: "Contaminant Control",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Contaminant+Control",
    },
    {
      lookupValue: "IntegratedPestManagement",
      lookupDisplayName: "Integrated Pest Management",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Integrated+Pest+Management",
    },
    {
      lookupValue: "MoistureControl",
      lookupDisplayName: "Moisture Control",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Moisture+Control",
    },
    {
      lookupValue: "Ventilation",
      lookupDisplayName: "Ventilation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ventilation",
    },
  ],
  GreenSustainability: [
    {
      lookupValue: "ConservingMethods",
      lookupDisplayName: "Conserving Methods",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Conserving+Methods",
    },
    {
      lookupValue: "OnsiteRecyclingCenter",
      lookupDisplayName: "Onsite Recycling Center",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Onsite+Recycling+Center",
    },
    {
      lookupValue: "RecyclableMaterials",
      lookupDisplayName: "Recyclable Materials",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Recyclable+Materials",
    },
    {
      lookupValue: "RecycledMaterials",
      lookupDisplayName: "Recycled Materials",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Recycled+Materials",
    },
    {
      lookupValue: "RegionallySourcedMaterials",
      lookupDisplayName: "Regionally-Sourced Materials",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Regionally-Sourced+Materials",
    },
    {
      lookupValue: "RenewableMaterials",
      lookupDisplayName: "Renewable Materials",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Renewable+Materials",
    },
    {
      lookupValue: "SalvagedMaterials",
      lookupDisplayName: "Salvaged Materials",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Salvaged+Materials",
    },
  ],
  GreenVerificationSource: [
    {
      lookupValue: "Administrator",
      lookupDisplayName: "Administrator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Administrator",
    },
    {
      lookupValue: "Assessor",
      lookupDisplayName: "Assessor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244921",
    },
    {
      lookupValue: "Builder",
      lookupDisplayName: "Builder",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244922",
    },
    {
      lookupValue: "ContractorOrInstaller",
      lookupDisplayName: "Contractor or Installer",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Contractor+or+Installer",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244924",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244925",
    },
    {
      lookupValue: "ProgramSponsor",
      lookupDisplayName: "Program Sponsor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Program+Sponsor",
    },
    {
      lookupValue: "ProgramVerifier",
      lookupDisplayName: "Program Verifier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Program+Verifier",
    },
    {
      lookupValue: "PublicRecords",
      lookupDisplayName: "Public Records",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244928",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244929",
    },
  ],
  GreenVerificationStatus: [
    {
      lookupValue: "Complete",
      lookupDisplayName: "Complete",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Complete",
    },
    {
      lookupValue: "InProcess",
      lookupDisplayName: "In Process",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Process",
    },
  ],
  GreenWaterConservation: [
    {
      lookupValue: "EfficientHotWaterDistribution",
      lookupDisplayName: "Efficient Hot Water Distribution",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Efficient+Hot+Water+Distribution",
    },
    {
      lookupValue: "GrayWaterSystem",
      lookupDisplayName: "Gray Water System",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gray+Water+System",
    },
    {
      lookupValue: "GreenInfrastructure",
      lookupDisplayName: "Green Infrastructure",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Green+Infrastructure",
    },
    {
      lookupValue: "LowFlowFixtures",
      lookupDisplayName: "Low-Flow Fixtures",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Low-Flow+Fixtures",
    },
    {
      lookupValue: "WaterRecycling",
      lookupDisplayName: "Water Recycling",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Recycling",
    },
    {
      lookupValue: "WaterSmartLandscaping",
      lookupDisplayName: "Water-Smart Landscaping",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Water-Smart+Landscaping",
    },
  ],
  Heating: [
    {
      lookupValue: "ActiveSolar",
      lookupDisplayName: "Active Solar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Active+Solar",
    },
    {
      lookupValue: "Baseboard",
      lookupDisplayName: "Baseboard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Baseboard",
    },
    {
      lookupValue: "Ceiling",
      lookupDisplayName: "Ceiling",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ceiling",
    },
    {
      lookupValue: "Central",
      lookupDisplayName: "Central",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Central",
    },
    {
      lookupValue: "Coal",
      lookupDisplayName: "Coal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Coal",
    },
    {
      lookupValue: "CoalStove",
      lookupDisplayName: "Coal Stove",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Coal+Stove",
    },
    {
      lookupValue: "Ductless",
      lookupDisplayName: "Ductless",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244958",
    },
    {
      lookupValue: "Electric",
      lookupDisplayName: "Electric",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244959",
    },
    {
      lookupValue: "EnergyStarAccaRsiQualifiedInstallation",
      lookupDisplayName: "ENERGY STAR/ACCA RSI Qualified Installation",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244960",
    },
    {
      lookupValue: "EnergyStarQualifiedEquipment",
      lookupDisplayName: "ENERGY STAR Qualified Equipment",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244961",
    },
    {
      lookupValue: "ExhaustFan",
      lookupDisplayName: "Exhaust Fan",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244962",
    },
    {
      lookupValue: "FireplaceInsert",
      lookupDisplayName: "Fireplace Insert",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fireplace+Insert",
    },
    {
      lookupValue: "Fireplaces",
      lookupDisplayName: "Fireplace(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244963",
    },
    {
      lookupValue: "FloorFurnace",
      lookupDisplayName: "Floor Furnace",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Floor+Furnace",
    },
    {
      lookupValue: "ForcedAir",
      lookupDisplayName: "Forced Air",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Forced+Air",
    },
    {
      lookupValue: "Geothermal",
      lookupDisplayName: "Geothermal",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244967",
    },
    {
      lookupValue: "Gravity",
      lookupDisplayName: "Gravity",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gravity",
    },
    {
      lookupValue: "HeatPump",
      lookupDisplayName: "Heat Pump",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244969",
    },
    {
      lookupValue: "HotWater",
      lookupDisplayName: "Hot Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244970",
    },
    {
      lookupValue: "HumidityControl",
      lookupDisplayName: "Humidity Control",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244971",
    },
    {
      lookupValue: "Kerosene",
      lookupDisplayName: "Kerosene",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kerosene",
    },
    {
      lookupValue: "NaturalGas",
      lookupDisplayName: "Natural Gas",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Natural+Gas",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244974",
    },
    {
      lookupValue: "Oil",
      lookupDisplayName: "Oil",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Oil",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244976",
    },
    {
      lookupValue: "PassiveSolar",
      lookupDisplayName: "Passive Solar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Passive+Solar",
    },
    {
      lookupValue: "PelletStove",
      lookupDisplayName: "Pellet Stove",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244978",
    },
    {
      lookupValue: "Propane",
      lookupDisplayName: "Propane",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244979",
    },
    {
      lookupValue: "PropaneStove",
      lookupDisplayName: "Propane Stove",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Propane+Stove",
    },
    {
      lookupValue: "Radiant",
      lookupDisplayName: "Radiant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Radiant",
    },
    {
      lookupValue: "RadiantCeiling",
      lookupDisplayName: "Radiant Ceiling",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Radiant+Ceiling",
    },
    {
      lookupValue: "RadiantFloor",
      lookupDisplayName: "Radiant Floor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Radiant+Floor",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244984",
    },
    {
      lookupValue: "SeparateMeters",
      lookupDisplayName: "Separate Meters",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244985",
    },
    {
      lookupValue: "Solar",
      lookupDisplayName: "Solar",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244986",
    },
    {
      lookupValue: "SpaceHeater",
      lookupDisplayName: "Space Heater",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Space+Heater",
    },
    {
      lookupValue: "Steam",
      lookupDisplayName: "Steam",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Steam",
    },
    {
      lookupValue: "VariesByUnit",
      lookupDisplayName: "Varies by Unit",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244989",
    },
    {
      lookupValue: "WallFurnace",
      lookupDisplayName: "Wall Furnace",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wall+Furnace",
    },
    {
      lookupValue: "Wood",
      lookupDisplayName: "Wood",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244991",
    },
    {
      lookupValue: "WoodStove",
      lookupDisplayName: "Wood Stove",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wood+Stove",
    },
    {
      lookupValue: "Zoned",
      lookupDisplayName: "Zoned",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244993",
    },
  ],
  HorseAmenities: [
    {
      lookupValue: "Arena",
      lookupDisplayName: "Arena",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244995",
    },
    {
      lookupValue: "Barn",
      lookupDisplayName: "Barn",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244996",
    },
    {
      lookupValue: "BoardingFacilities",
      lookupDisplayName: "Boarding Facilities",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Boarding+Facilities",
    },
    {
      lookupValue: "Corrals",
      lookupDisplayName: "Corral(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244998",
    },
    {
      lookupValue: "HayStorage",
      lookupDisplayName: "Hay Storage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hay+Storage",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245000",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245001",
    },
    {
      lookupValue: "Paddocks",
      lookupDisplayName: "Paddocks",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Paddocks",
    },
    {
      lookupValue: "PalpationChute",
      lookupDisplayName: "Palpation Chute",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Palpation+Chute",
    },
    {
      lookupValue: "Pasture",
      lookupDisplayName: "Pasture",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245004",
    },
    {
      lookupValue: "RidingTrail",
      lookupDisplayName: "Riding Trail",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Riding+Trail",
    },
    {
      lookupValue: "RoundPen",
      lookupDisplayName: "Round Pen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Round+Pen",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245007",
    },
    {
      lookupValue: "ShavingBin",
      lookupDisplayName: "Shaving Bin",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shaving+Bin",
    },
    {
      lookupValue: "Stables",
      lookupDisplayName: "Stable(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245009",
    },
    {
      lookupValue: "TackRoom",
      lookupDisplayName: "Tack Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tack+Room",
    },
    {
      lookupValue: "TrailerStorage",
      lookupDisplayName: "Trailer Storage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Trailer+Storage",
    },
    {
      lookupValue: "WashRack",
      lookupDisplayName: "Wash Rack",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wash+Rack",
    },
  ],
  HoursDaysOfOperation: [
    {
      lookupValue: "EveningsOnly",
      lookupDisplayName: "Evenings Only",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Evenings+Only",
    },
    {
      lookupValue: "Open24Hours",
      lookupDisplayName: "Open 24 Hours",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+24+Hours",
    },
    {
      lookupValue: "Open7Days",
      lookupDisplayName: "Open 7 Days",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+7+Days",
    },
    {
      lookupValue: "Open8HoursDay",
      lookupDisplayName: "Open 8 Hours/Day",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244945",
    },
    {
      lookupValue: "OpenLessThan8HoursDay",
      lookupDisplayName: "Open -8 Hours/Day",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244946",
    },
    {
      lookupValue: "OpenMondayFriday",
      lookupDisplayName: "Open Monday-Friday",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+Monday-Friday",
    },
    {
      lookupValue: "OpenOver8HoursDay",
      lookupDisplayName: "Open 8+ Hours/Day",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29244947",
    },
    {
      lookupValue: "OpenSaturday",
      lookupDisplayName: "Open Saturday",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+Saturday",
    },
    {
      lookupValue: "OpenSunday",
      lookupDisplayName: "Open Sunday",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+Sunday",
    },
  ],
  ImageOf: [
    {
      lookupValue: "AerialView",
      lookupDisplayName: "Aerial View",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aerial+View",
    },
    {
      lookupValue: "Atrium",
      lookupDisplayName: "Atrium",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Atrium",
    },
    {
      lookupValue: "Attic",
      lookupDisplayName: "Attic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Attic",
    },
    {
      lookupValue: "BackOfStructure",
      lookupDisplayName: "Back of Structure",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Back+of+Structure",
    },
    {
      lookupValue: "Balcony",
      lookupDisplayName: "Balcony",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Balcony",
    },
    {
      lookupValue: "Bar",
      lookupDisplayName: "Bar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bar",
    },
    {
      lookupValue: "Barn",
      lookupDisplayName: "Barn",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Barn",
    },
    {
      lookupValue: "Basement",
      lookupDisplayName: "Basement",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Basement",
    },
    {
      lookupValue: "Bathroom",
      lookupDisplayName: "Bathroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bathroom",
    },
    {
      lookupValue: "Bedroom",
      lookupDisplayName: "Bedroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bedroom",
    },
    {
      lookupValue: "BonusRoom",
      lookupDisplayName: "Bonus Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bonus+Room",
    },
    {
      lookupValue: "BreakfastArea",
      lookupDisplayName: "Breakfast Area",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Breakfast+Area",
    },
    {
      lookupValue: "Closet",
      lookupDisplayName: "Closet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Closet",
    },
    {
      lookupValue: "Community",
      lookupDisplayName: "Community",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Community",
    },
    {
      lookupValue: "Courtyard",
      lookupDisplayName: "Courtyard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Courtyard",
    },
    {
      lookupValue: "Deck",
      lookupDisplayName: "Deck",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Deck",
    },
    {
      lookupValue: "Den",
      lookupDisplayName: "Den",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Den",
    },
    {
      lookupValue: "DiningArea",
      lookupDisplayName: "Dining Area",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dining+Area",
    },
    {
      lookupValue: "DiningRoom",
      lookupDisplayName: "Dining Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dining+Room",
    },
    {
      lookupValue: "Dock",
      lookupDisplayName: "Dock",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dock",
    },
    {
      lookupValue: "Entry",
      lookupDisplayName: "Entry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Entry",
    },
    {
      lookupValue: "ExerciseRoom",
      lookupDisplayName: "Exercise Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Exercise+Room",
    },
    {
      lookupValue: "FamilyRoom",
      lookupDisplayName: "Family Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Family+Room",
    },
    {
      lookupValue: "Fence",
      lookupDisplayName: "Fence",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fence",
    },
    {
      lookupValue: "Fireplace",
      lookupDisplayName: "Fireplace",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fireplace",
    },
    {
      lookupValue: "FloorPlan",
      lookupDisplayName: "Floor Plan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Floor+Plan",
    },
    {
      lookupValue: "FrontOfStructure",
      lookupDisplayName: "Front of Structure",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Front+of+Structure",
    },
    {
      lookupValue: "GameRoom",
      lookupDisplayName: "Game Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Game+Room",
    },
    {
      lookupValue: "Garage",
      lookupDisplayName: "Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Garage",
    },
    {
      lookupValue: "Garden",
      lookupDisplayName: "Garden",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Garden",
    },
    {
      lookupValue: "GolfCourse",
      lookupDisplayName: "Golf Course",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Golf+Course",
    },
    {
      lookupValue: "GreatRoom",
      lookupDisplayName: "Great Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Great+Room",
    },
    {
      lookupValue: "GuestQuarters",
      lookupDisplayName: "Guest Quarters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Guest+Quarters",
    },
    {
      lookupValue: "Gym",
      lookupDisplayName: "Gym",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gym",
    },
    {
      lookupValue: "HobbyRoom",
      lookupDisplayName: "Hobby Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hobby+Room",
    },
    {
      lookupValue: "Inlaw",
      lookupDisplayName: "Inlaw",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Inlaw",
    },
    {
      lookupValue: "Kitchen",
      lookupDisplayName: "Kitchen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kitchen",
    },
    {
      lookupValue: "Lake",
      lookupDisplayName: "Lake",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lake",
    },
    {
      lookupValue: "Laundry",
      lookupDisplayName: "Laundry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Laundry",
    },
    {
      lookupValue: "Library",
      lookupDisplayName: "Library",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Library",
    },
    {
      lookupValue: "LivingRoom",
      lookupDisplayName: "Living Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Living+Room",
    },
    {
      lookupValue: "LoadingDock",
      lookupDisplayName: "Loading Dock",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Loading+Dock",
    },
    {
      lookupValue: "Lobby",
      lookupDisplayName: "Lobby",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lobby",
    },
    {
      lookupValue: "Loft",
      lookupDisplayName: "Loft",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Loft",
    },
    {
      lookupValue: "Lot",
      lookupDisplayName: "Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lot",
    },
    {
      lookupValue: "MasterBathroom",
      lookupDisplayName: "Master Bathroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Master+Bathroom",
    },
    {
      lookupValue: "MasterBedroom",
      lookupDisplayName: "Master Bedroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Master+Bedroom",
    },
    {
      lookupValue: "MediaRoom",
      lookupDisplayName: "Media Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Media+Room",
    },
    {
      lookupValue: "MudRoom",
      lookupDisplayName: "Mud Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mud+Room",
    },
    {
      lookupValue: "Nursery",
      lookupDisplayName: "Nursery",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Nursery",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245065",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245066",
    },
    {
      lookupValue: "OutBuildings",
      lookupDisplayName: "Out Buildings",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Out+Buildings",
    },
    {
      lookupValue: "Pantry",
      lookupDisplayName: "Pantry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pantry",
    },
    {
      lookupValue: "Parking",
      lookupDisplayName: "Parking",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245069",
    },
    {
      lookupValue: "Patio",
      lookupDisplayName: "Patio",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Patio",
    },
    {
      lookupValue: "Pier",
      lookupDisplayName: "Pier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pier",
    },
    {
      lookupValue: "PlatMap",
      lookupDisplayName: "Plat Map",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Plat+Map",
    },
    {
      lookupValue: "Pond",
      lookupDisplayName: "Pond",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pond",
    },
    {
      lookupValue: "Pool",
      lookupDisplayName: "Pool",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pool",
    },
    {
      lookupValue: "Reception",
      lookupDisplayName: "Reception",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Reception",
    },
    {
      lookupValue: "RecreationRoom",
      lookupDisplayName: "Recreation Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Recreation+Room",
    },
    {
      lookupValue: "Sauna",
      lookupDisplayName: "Sauna",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sauna",
    },
    {
      lookupValue: "Showroom",
      lookupDisplayName: "Showroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Showroom",
    },
    {
      lookupValue: "SideOfStructure",
      lookupDisplayName: "Side of Structure",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Side+of+Structure",
    },
    {
      lookupValue: "SittingRoom",
      lookupDisplayName: "Sitting Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sitting+Room",
    },
    {
      lookupValue: "Spa",
      lookupDisplayName: "Spa",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Spa",
    },
    {
      lookupValue: "Stable",
      lookupDisplayName: "Stable",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stable",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245083",
    },
    {
      lookupValue: "Studio",
      lookupDisplayName: "Studio",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Studio",
    },
    {
      lookupValue: "Study",
      lookupDisplayName: "Study",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Study",
    },
    {
      lookupValue: "SunRoom",
      lookupDisplayName: "Sun Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sun+Room",
    },
    {
      lookupValue: "View",
      lookupDisplayName: "View",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/View",
    },
    {
      lookupValue: "Waterfront",
      lookupDisplayName: "Waterfront",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Waterfront",
    },
    {
      lookupValue: "WineCellar",
      lookupDisplayName: "Wine Cellar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wine+Cellar",
    },
    {
      lookupValue: "Workshop",
      lookupDisplayName: "Workshop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Workshop",
    },
    {
      lookupValue: "Yard",
      lookupDisplayName: "Yard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Yard",
    },
  ],
  IncomeIncludes: [
    {
      lookupValue: "Laundry",
      lookupDisplayName: "Laundry",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245093",
    },
    {
      lookupValue: "Parking",
      lookupDisplayName: "Parking",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245094",
    },
    {
      lookupValue: "Recreation",
      lookupDisplayName: "Recreation",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245095",
    },
    {
      lookupValue: "RentOnly",
      lookupDisplayName: "Rent Only",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rent+Only",
    },
    {
      lookupValue: "RvStorage",
      lookupDisplayName: "RV Storage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RV+Storage",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245098",
    },
  ],
  InteriorOrRoomFeatures: [
    {
      lookupValue: "Bar",
      lookupDisplayName: "Bar",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245100",
    },
    {
      lookupValue: "BeamedCeilings",
      lookupDisplayName: "Beamed Ceilings",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Beamed+Ceilings",
    },
    {
      lookupValue: "Bidet",
      lookupDisplayName: "Bidet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bidet",
    },
    {
      lookupValue: "Bookcases",
      lookupDisplayName: "Bookcases",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bookcases",
    },
    {
      lookupValue: "BreakfastBar",
      lookupDisplayName: "Breakfast Bar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Breakfast+Bar",
    },
    {
      lookupValue: "BuiltInFeatures",
      lookupDisplayName: "Built-in Features",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Built-in+Features",
    },
    {
      lookupValue: "CathedralCeilings",
      lookupDisplayName: "Cathedral Ceiling(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245106",
    },
    {
      lookupValue: "CedarClosets",
      lookupDisplayName: "Cedar Closet(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245107",
    },
    {
      lookupValue: "CeilingFans",
      lookupDisplayName: "Ceiling Fan(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245108",
    },
    {
      lookupValue: "CentralVacuum",
      lookupDisplayName: "Central Vacuum",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Central+Vacuum",
    },
    {
      lookupValue: "Chandelier",
      lookupDisplayName: "Chandelier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chandelier",
    },
    {
      lookupValue: "CofferedCeilings",
      lookupDisplayName: "Coffered Ceiling(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245111",
    },
    {
      lookupValue: "CrownMolding",
      lookupDisplayName: "Crown Molding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Crown+Molding",
    },
    {
      lookupValue: "DoubleVanity",
      lookupDisplayName: "Double Vanity",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Double+Vanity",
    },
    {
      lookupValue: "DryBar",
      lookupDisplayName: "Dry Bar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dry+Bar",
    },
    {
      lookupValue: "Dumbwaiter",
      lookupDisplayName: "Dumbwaiter",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dumbwaiter",
    },
    {
      lookupValue: "EatInKitchen",
      lookupDisplayName: "Eat-in Kitchen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Eat-in+Kitchen",
    },
    {
      lookupValue: "Elevator",
      lookupDisplayName: "Elevator",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Elevator",
    },
    {
      lookupValue: "EntranceFoyer",
      lookupDisplayName: "Entrance Foyer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Entrance+Foyer",
    },
    {
      lookupValue: "GraniteCounters",
      lookupDisplayName: "Granite Counters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Granite+Counters",
    },
    {
      lookupValue: "HighCeilings",
      lookupDisplayName: "High Ceilings",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/High+Ceilings",
    },
    {
      lookupValue: "HighSpeedInternet",
      lookupDisplayName: "High Speed Internet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/High+Speed+Internet",
    },
    {
      lookupValue: "HisAndHersClosets",
      lookupDisplayName: "His and Hers Closets",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/His+and+Hers+Closets",
    },
    {
      lookupValue: "InLawFloorplan",
      lookupDisplayName: "In-Law Floorplan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In-Law+Floorplan",
    },
    {
      lookupValue: "KitchenIsland",
      lookupDisplayName: "Kitchen Island",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kitchen+Island",
    },
    {
      lookupValue: "LaminateCounters",
      lookupDisplayName: "Laminate Counters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Laminate+Counters",
    },
    {
      lookupValue: "LowFlowPlumbingFixtures",
      lookupDisplayName: "Low Flow Plumbing Fixtures",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Low+Flow+Plumbing+Fixtures",
    },
    {
      lookupValue: "MasterDownstairs",
      lookupDisplayName: "Master Downstairs",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Master+Downstairs",
    },
    {
      lookupValue: "NaturalWoodwork",
      lookupDisplayName: "Natural Woodwork",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Natural+Woodwork",
    },
    {
      lookupValue: "OpenFloorplan",
      lookupDisplayName: "Open Floorplan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+Floorplan",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245130",
    },
    {
      lookupValue: "Pantry",
      lookupDisplayName: "Pantry",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245131",
    },
    {
      lookupValue: "RecessedLighting",
      lookupDisplayName: "Recessed Lighting",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Recessed+Lighting",
    },
    {
      lookupValue: "Sauna",
      lookupDisplayName: "Sauna",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245133",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245134",
    },
    {
      lookupValue: "SmartHome",
      lookupDisplayName: "Smart Home",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Smart+Home",
    },
    {
      lookupValue: "SmartThermostat",
      lookupDisplayName: "Smart Thermostat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Smart+Thermostat",
    },
    {
      lookupValue: "SoakingTub",
      lookupDisplayName: "Soaking Tub",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Soaking+Tub",
    },
    {
      lookupValue: "SolarTubes",
      lookupDisplayName: "Solar Tube(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245138",
    },
    {
      lookupValue: "SoundSystem",
      lookupDisplayName: "Sound System",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sound+System",
    },
    {
      lookupValue: "StoneCounters",
      lookupDisplayName: "Stone Counters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stone+Counters",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245141",
    },
    {
      lookupValue: "TileCounters",
      lookupDisplayName: "Tile Counters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tile+Counters",
    },
    {
      lookupValue: "TrackLighting",
      lookupDisplayName: "Track Lighting",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Track+Lighting",
    },
    {
      lookupValue: "TrayCeilings",
      lookupDisplayName: "Tray Ceiling(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245144",
    },
    {
      lookupValue: "VaultedCeilings",
      lookupDisplayName: "Vaulted Ceiling(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245145",
    },
    {
      lookupValue: "WalkInClosets",
      lookupDisplayName: "Walk-In Closet(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245146",
    },
    {
      lookupValue: "WaterSenseFixtures",
      lookupDisplayName: "WaterSense Fixture(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245147",
    },
    {
      lookupValue: "WetBar",
      lookupDisplayName: "Wet Bar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wet+Bar",
    },
    {
      lookupValue: "WiredForData",
      lookupDisplayName: "Wired for Data",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wired+for+Data",
    },
    {
      lookupValue: "WiredForSound",
      lookupDisplayName: "Wired for Sound",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wired+for+Sound",
    },
  ],
  LaborInformation: [
    {
      lookupValue: "EmployeeLicenseRequired",
      lookupDisplayName: "Employee License Required",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Employee+License+Required",
    },
    {
      lookupValue: "NonUnion",
      lookupDisplayName: "Non-Union",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Non-Union",
    },
    {
      lookupValue: "Union",
      lookupDisplayName: "Union",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Union",
    },
  ],
  Languages: [
    {
      lookupValue: "Abkhazian",
      lookupDisplayName: "Abkhazian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Abkhazian",
    },
    {
      lookupValue: "Afar",
      lookupDisplayName: "Afar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Afar",
    },
    {
      lookupValue: "Afrikaans",
      lookupDisplayName: "Afrikaans",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Afrikaans",
    },
    {
      lookupValue: "Albanian",
      lookupDisplayName: "Albanian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Albanian",
    },
    {
      lookupValue: "AmericanSignLanguage",
      lookupDisplayName: "American Sign Language",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/American+Sign+Language",
    },
    {
      lookupValue: "Amharic",
      lookupDisplayName: "Amharic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Amharic",
    },
    {
      lookupValue: "Arabic",
      lookupDisplayName: "Arabic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Arabic",
    },
    {
      lookupValue: "Aramaic",
      lookupDisplayName: "Aramaic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aramaic",
    },
    {
      lookupValue: "Armenian",
      lookupDisplayName: "Armenian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Armenian",
    },
    {
      lookupValue: "Assamese",
      lookupDisplayName: "Assamese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Assamese",
    },
    {
      lookupValue: "AssyrianNeoAramaic",
      lookupDisplayName: "Assyrian Neo-Aramaic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Assyrian+Neo-Aramaic",
    },
    {
      lookupValue: "Avestan",
      lookupDisplayName: "Avestan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Avestan",
    },
    {
      lookupValue: "Aymara",
      lookupDisplayName: "Aymara",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aymara",
    },
    {
      lookupValue: "Azerbaijani",
      lookupDisplayName: "Azerbaijani",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Azerbaijani",
    },
    {
      lookupValue: "Bambara",
      lookupDisplayName: "Bambara",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bambara",
    },
    {
      lookupValue: "Bashkir",
      lookupDisplayName: "Bashkir",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bashkir",
    },
    {
      lookupValue: "Basque",
      lookupDisplayName: "Basque",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Basque",
    },
    {
      lookupValue: "Bengali",
      lookupDisplayName: "Bengali",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bengali",
    },
    {
      lookupValue: "Bihari",
      lookupDisplayName: "Bihari",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bihari",
    },
    {
      lookupValue: "Bikol",
      lookupDisplayName: "Bikol",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bikol",
    },
    {
      lookupValue: "Bislama",
      lookupDisplayName: "Bislama",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bislama",
    },
    {
      lookupValue: "Bosnian",
      lookupDisplayName: "Bosnian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bosnian",
    },
    {
      lookupValue: "BrazilianPortuguese",
      lookupDisplayName: "Brazilian Portuguese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Brazilian+Portuguese",
    },
    {
      lookupValue: "Bulgarian",
      lookupDisplayName: "Bulgarian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bulgarian",
    },
    {
      lookupValue: "Burmese",
      lookupDisplayName: "Burmese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Burmese",
    },
    {
      lookupValue: "Byelorussian",
      lookupDisplayName: "Byelorussian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Byelorussian",
    },
    {
      lookupValue: "Cambodian",
      lookupDisplayName: "Cambodian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cambodian",
    },
    {
      lookupValue: "Cantonese",
      lookupDisplayName: "Cantonese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cantonese",
    },
    {
      lookupValue: "CapeVerdeanCreole",
      lookupDisplayName: "Cape Verdean Creole",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cape+Verdean+Creole",
    },
    {
      lookupValue: "Catalan",
      lookupDisplayName: "Catalan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Catalan",
    },
    {
      lookupValue: "Cebuano",
      lookupDisplayName: "Cebuano",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cebuano",
    },
    {
      lookupValue: "Chamorro",
      lookupDisplayName: "Chamorro",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chamorro",
    },
    {
      lookupValue: "Chechen",
      lookupDisplayName: "Chechen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chechen",
    },
    {
      lookupValue: "Chinese",
      lookupDisplayName: "Chinese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chinese",
    },
    {
      lookupValue: "Chuukese",
      lookupDisplayName: "Chuukese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chuukese",
    },
    {
      lookupValue: "Chuvash",
      lookupDisplayName: "Chuvash",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chuvash",
    },
    {
      lookupValue: "Cornish",
      lookupDisplayName: "Cornish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cornish",
    },
    {
      lookupValue: "Corsican",
      lookupDisplayName: "Corsican",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Corsican",
    },
    {
      lookupValue: "Croatian",
      lookupDisplayName: "Croatian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Croatian",
    },
    {
      lookupValue: "Czech",
      lookupDisplayName: "Czech",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Czech",
    },
    {
      lookupValue: "Danish",
      lookupDisplayName: "Danish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Danish",
    },
    {
      lookupValue: "Dari",
      lookupDisplayName: "Dari (Afghan Persian)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245194",
    },
    {
      lookupValue: "Dioula",
      lookupDisplayName: "Dioula",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dioula",
    },
    {
      lookupValue: "Dutch",
      lookupDisplayName: "Dutch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dutch",
    },
    {
      lookupValue: "Dzongkha",
      lookupDisplayName: "Dzongkha",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dzongkha",
    },
    {
      lookupValue: "English",
      lookupDisplayName: "English",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/English",
    },
    {
      lookupValue: "Esperanto",
      lookupDisplayName: "Esperanto",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Esperanto",
    },
    {
      lookupValue: "Estonian",
      lookupDisplayName: "Estonian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Estonian",
    },
    {
      lookupValue: "Faroese",
      lookupDisplayName: "Faroese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Faroese",
    },
    {
      lookupValue: "Farsi",
      lookupDisplayName: "Farsi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Farsi",
    },
    {
      lookupValue: "Fiji",
      lookupDisplayName: "Fiji",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fiji",
    },
    {
      lookupValue: "Finnish",
      lookupDisplayName: "Finnish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Finnish",
    },
    {
      lookupValue: "Flemish",
      lookupDisplayName: "Flemish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Flemish",
    },
    {
      lookupValue: "French",
      lookupDisplayName: "French",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/French",
    },
    {
      lookupValue: "Frisian",
      lookupDisplayName: "Frisian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Frisian",
    },
    {
      lookupValue: "Galician",
      lookupDisplayName: "Galician",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Galician",
    },
    {
      lookupValue: "Georgian",
      lookupDisplayName: "Georgian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Georgian",
    },
    {
      lookupValue: "German",
      lookupDisplayName: "German",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/German",
    },
    {
      lookupValue: "Greek",
      lookupDisplayName: "Greek",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Greek",
    },
    {
      lookupValue: "Greenlandic",
      lookupDisplayName: "Greenlandic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Greenlandic",
    },
    {
      lookupValue: "Guarani",
      lookupDisplayName: "Guarani",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Guarani",
    },
    {
      lookupValue: "Gujarati",
      lookupDisplayName: "Gujarati",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gujarati",
    },
    {
      lookupValue: "HaitianCreole",
      lookupDisplayName: "Haitian Creole",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Haitian+Creole",
    },
    {
      lookupValue: "Hausa",
      lookupDisplayName: "Hausa",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hausa",
    },
    {
      lookupValue: "Hebrew",
      lookupDisplayName: "Hebrew",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hebrew",
    },
    {
      lookupValue: "Herero",
      lookupDisplayName: "Herero",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Herero",
    },
    {
      lookupValue: "Hiligaynon",
      lookupDisplayName: "Hiligaynon",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hiligaynon",
    },
    {
      lookupValue: "Hindi",
      lookupDisplayName: "Hindi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hindi",
    },
    {
      lookupValue: "HiriMotu",
      lookupDisplayName: "Hiri Motu",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hiri+Motu",
    },
    {
      lookupValue: "Hmong",
      lookupDisplayName: "Hmong",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hmong",
    },
    {
      lookupValue: "Hungarian",
      lookupDisplayName: "Hungarian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hungarian",
    },
    {
      lookupValue: "Iban",
      lookupDisplayName: "Iban",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Iban",
    },
    {
      lookupValue: "Icelandic",
      lookupDisplayName: "Icelandic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Icelandic",
    },
    {
      lookupValue: "Igbo",
      lookupDisplayName: "Igbo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Igbo",
    },
    {
      lookupValue: "Ilocano",
      lookupDisplayName: "Ilocano",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ilocano",
    },
    {
      lookupValue: "Indonesian",
      lookupDisplayName: "Indonesian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Indonesian",
    },
    {
      lookupValue: "Interlingua",
      lookupDisplayName: "Interlingua",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Interlingua",
    },
    {
      lookupValue: "Inuktitut",
      lookupDisplayName: "Inuktitut",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Inuktitut",
    },
    {
      lookupValue: "Inupiak",
      lookupDisplayName: "Inupiak",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Inupiak",
    },
    {
      lookupValue: "Irish",
      lookupDisplayName: "Irish (Gaelic)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245232",
    },
    {
      lookupValue: "Italian",
      lookupDisplayName: "Italian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Italian",
    },
    {
      lookupValue: "Japanese",
      lookupDisplayName: "Japanese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Japanese",
    },
    {
      lookupValue: "Javanese",
      lookupDisplayName: "Javanese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Javanese",
    },
    {
      lookupValue: "Kannada",
      lookupDisplayName: "Kannada",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kannada",
    },
    {
      lookupValue: "Kashmiri",
      lookupDisplayName: "Kashmiri",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kashmiri",
    },
    {
      lookupValue: "Kazakh",
      lookupDisplayName: "Kazakh",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kazakh",
    },
    {
      lookupValue: "Kiche",
      lookupDisplayName: "K'iche'",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245239",
    },
    {
      lookupValue: "Kichwa",
      lookupDisplayName: "Kichwa",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kichwa",
    },
    {
      lookupValue: "Kikuyu",
      lookupDisplayName: "Kikuyu",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kikuyu",
    },
    {
      lookupValue: "Kinyarwanda",
      lookupDisplayName: "Kinyarwanda",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kinyarwanda",
    },
    {
      lookupValue: "Kirghiz",
      lookupDisplayName: "Kirghiz",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kirghiz",
    },
    {
      lookupValue: "Kirundi",
      lookupDisplayName: "Kirundi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kirundi",
    },
    {
      lookupValue: "Komi",
      lookupDisplayName: "Komi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Komi",
    },
    {
      lookupValue: "Korean",
      lookupDisplayName: "Korean",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Korean",
    },
    {
      lookupValue: "Kpelle",
      lookupDisplayName: "Kpelle",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kpelle",
    },
    {
      lookupValue: "Kru",
      lookupDisplayName: "Kru",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kru",
    },
    {
      lookupValue: "Kurdish",
      lookupDisplayName: "Kurdish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kurdish",
    },
    {
      lookupValue: "Lao",
      lookupDisplayName: "Lao",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lao",
    },
    {
      lookupValue: "Latin",
      lookupDisplayName: "Latin",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Latin",
    },
    {
      lookupValue: "Latvian",
      lookupDisplayName: "Latvian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Latvian",
    },
    {
      lookupValue: "Lingala",
      lookupDisplayName: "Lingala",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lingala",
    },
    {
      lookupValue: "Lithuanian",
      lookupDisplayName: "Lithuanian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lithuanian",
    },
    {
      lookupValue: "Luxemburgish",
      lookupDisplayName: "Luxemburgish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Luxemburgish",
    },
    {
      lookupValue: "Macedonian",
      lookupDisplayName: "Macedonian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Macedonian",
    },
    {
      lookupValue: "Malagasy",
      lookupDisplayName: "Malagasy",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Malagasy",
    },
    {
      lookupValue: "Malay",
      lookupDisplayName: "Malay",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Malay",
    },
    {
      lookupValue: "Malayalam",
      lookupDisplayName: "Malayalam",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Malayalam",
    },
    {
      lookupValue: "Maltese",
      lookupDisplayName: "Maltese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Maltese",
    },
    {
      lookupValue: "Mandarin",
      lookupDisplayName: "Mandarin",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mandarin",
    },
    {
      lookupValue: "Maninka",
      lookupDisplayName: "Maninka",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Maninka",
    },
    {
      lookupValue: "ManxGaelic",
      lookupDisplayName: "Manx Gaelic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manx+Gaelic",
    },
    {
      lookupValue: "Maori",
      lookupDisplayName: "Maori",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Maori",
    },
    {
      lookupValue: "Marathi",
      lookupDisplayName: "Marathi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Marathi",
    },
    {
      lookupValue: "Marshallese",
      lookupDisplayName: "Marshallese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Marshallese",
    },
    {
      lookupValue: "Moldovan",
      lookupDisplayName: "Moldovan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Moldovan",
    },
    {
      lookupValue: "Mongolian",
      lookupDisplayName: "Mongolian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mongolian",
    },
    {
      lookupValue: "Nauru",
      lookupDisplayName: "Nauru",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Nauru",
    },
    {
      lookupValue: "Navajo",
      lookupDisplayName: "Navajo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Navajo",
    },
    {
      lookupValue: "Ndebele",
      lookupDisplayName: "Ndebele",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ndebele",
    },
    {
      lookupValue: "Ndonga",
      lookupDisplayName: "Ndonga",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ndonga",
    },
    {
      lookupValue: "Nepali",
      lookupDisplayName: "Nepali",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Nepali",
    },
    {
      lookupValue: "Norwegian",
      lookupDisplayName: "Norwegian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Norwegian",
    },
    {
      lookupValue: "Nyanja",
      lookupDisplayName: "Nyanja",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Nyanja",
    },
    {
      lookupValue: "Occitan",
      lookupDisplayName: "Occitan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Occitan",
    },
    {
      lookupValue: "Oriya",
      lookupDisplayName: "Oriya",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Oriya",
    },
    {
      lookupValue: "Oromo",
      lookupDisplayName: "Oromo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Oromo",
    },
    {
      lookupValue: "Ossetian",
      lookupDisplayName: "Ossetian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ossetian",
    },
    {
      lookupValue: "Pali",
      lookupDisplayName: "Pali",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pali",
    },
    {
      lookupValue: "Pangasinan",
      lookupDisplayName: "Pangasinan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pangasinan",
    },
    {
      lookupValue: "Papiamento",
      lookupDisplayName: "Papiamento",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Papiamento",
    },
    {
      lookupValue: "Pashto",
      lookupDisplayName: "Pashto",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pashto",
    },
    {
      lookupValue: "Polish",
      lookupDisplayName: "Polish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Polish",
    },
    {
      lookupValue: "Portuguese",
      lookupDisplayName: "Portuguese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Portuguese",
    },
    {
      lookupValue: "Punjabi",
      lookupDisplayName: "Punjabi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Punjabi",
    },
    {
      lookupValue: "Quechua",
      lookupDisplayName: "Quechua",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Quechua",
    },
    {
      lookupValue: "Romanian",
      lookupDisplayName: "Romanian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Romanian",
    },
    {
      lookupValue: "Romany",
      lookupDisplayName: "Romany",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Romany",
    },
    {
      lookupValue: "Russian",
      lookupDisplayName: "Russian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Russian",
    },
    {
      lookupValue: "Sami",
      lookupDisplayName: "Sami",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sami",
    },
    {
      lookupValue: "Samoan",
      lookupDisplayName: "Samoan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Samoan",
    },
    {
      lookupValue: "Sangho",
      lookupDisplayName: "Sangho",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sangho",
    },
    {
      lookupValue: "Sanskrit",
      lookupDisplayName: "Sanskrit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sanskrit",
    },
    {
      lookupValue: "Sardinian",
      lookupDisplayName: "Sardinian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sardinian",
    },
    {
      lookupValue: "ScotsGaelic",
      lookupDisplayName: "Scots Gaelic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Scots+Gaelic",
    },
    {
      lookupValue: "Serbian",
      lookupDisplayName: "Serbian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Serbian",
    },
    {
      lookupValue: "SerboCroatian",
      lookupDisplayName: "Serbo-Croatian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Serbo-Croatian",
    },
    {
      lookupValue: "Sesotho",
      lookupDisplayName: "Sesotho",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sesotho",
    },
    {
      lookupValue: "Setswana",
      lookupDisplayName: "Setswana",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Setswana",
    },
    {
      lookupValue: "Shan",
      lookupDisplayName: "Shan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shan",
    },
    {
      lookupValue: "Shona",
      lookupDisplayName: "Shona",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shona",
    },
    {
      lookupValue: "Sindhi",
      lookupDisplayName: "Sindhi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sindhi",
    },
    {
      lookupValue: "Sinhalese",
      lookupDisplayName: "Sinhalese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sinhalese",
    },
    {
      lookupValue: "Siswati",
      lookupDisplayName: "Siswati",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Siswati",
    },
    {
      lookupValue: "Slovak",
      lookupDisplayName: "Slovak",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Slovak",
    },
    {
      lookupValue: "Slovenian",
      lookupDisplayName: "Slovenian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Slovenian",
    },
    {
      lookupValue: "Somali",
      lookupDisplayName: "Somali",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Somali",
    },
    {
      lookupValue: "SouthernNdebele",
      lookupDisplayName: "Southern Ndebele",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Southern+Ndebele",
    },
    {
      lookupValue: "Spanish",
      lookupDisplayName: "Spanish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Spanish",
    },
    {
      lookupValue: "Sundanese",
      lookupDisplayName: "Sundanese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sundanese",
    },
    {
      lookupValue: "Swahili",
      lookupDisplayName: "Swahili",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Swahili",
    },
    {
      lookupValue: "Swedish",
      lookupDisplayName: "Swedish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Swedish",
    },
    {
      lookupValue: "Syriac",
      lookupDisplayName: "Syriac",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Syriac",
    },
    {
      lookupValue: "Tagalog",
      lookupDisplayName: "Tagalog",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tagalog",
    },
    {
      lookupValue: "Tahitian",
      lookupDisplayName: "Tahitian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tahitian",
    },
    {
      lookupValue: "Tajik",
      lookupDisplayName: "Tajik",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tajik",
    },
    {
      lookupValue: "Tamil",
      lookupDisplayName: "Tamil",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tamil",
    },
    {
      lookupValue: "Tatar",
      lookupDisplayName: "Tatar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tatar",
    },
    {
      lookupValue: "Telugu",
      lookupDisplayName: "Telugu",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Telugu",
    },
    {
      lookupValue: "Thai",
      lookupDisplayName: "Thai",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Thai",
    },
    {
      lookupValue: "Tibetan",
      lookupDisplayName: "Tibetan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tibetan",
    },
    {
      lookupValue: "Tigrinya",
      lookupDisplayName: "Tigrinya",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tigrinya",
    },
    {
      lookupValue: "Tongan",
      lookupDisplayName: "Tongan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tongan",
    },
    {
      lookupValue: "Tsonga",
      lookupDisplayName: "Tsonga",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tsonga",
    },
    {
      lookupValue: "Turkish",
      lookupDisplayName: "Turkish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Turkish",
    },
    {
      lookupValue: "Turkmen",
      lookupDisplayName: "Turkmen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Turkmen",
    },
    {
      lookupValue: "Twi",
      lookupDisplayName: "Twi",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Twi",
    },
    {
      lookupValue: "Uigur",
      lookupDisplayName: "Uigur",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Uigur",
    },
    {
      lookupValue: "Ukrainian",
      lookupDisplayName: "Ukrainian",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ukrainian",
    },
    {
      lookupValue: "Urdu",
      lookupDisplayName: "Urdu",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Urdu",
    },
    {
      lookupValue: "Uzbek",
      lookupDisplayName: "Uzbek",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Uzbek",
    },
    {
      lookupValue: "Vietnamese",
      lookupDisplayName: "Vietnamese",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vietnamese",
    },
    {
      lookupValue: "Volapuk",
      lookupDisplayName: "Volapuk",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Volapuk",
    },
    {
      lookupValue: "Welsh",
      lookupDisplayName: "Welsh",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Welsh",
    },
    {
      lookupValue: "Wolof",
      lookupDisplayName: "Wolof",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wolof",
    },
    {
      lookupValue: "Xhosa",
      lookupDisplayName: "Xhosa",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Xhosa",
    },
    {
      lookupValue: "Yiddish",
      lookupDisplayName: "Yiddish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Yiddish",
    },
    {
      lookupValue: "Yoruba",
      lookupDisplayName: "Yoruba",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Yoruba",
    },
    {
      lookupValue: "Zhuang",
      lookupDisplayName: "Zhuang",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Zhuang",
    },
    {
      lookupValue: "Zulu",
      lookupDisplayName: "Zulu",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Zulu",
    },
  ],
  LaundryFeatures: [
    {
      lookupValue: "CommonArea",
      lookupDisplayName: "Common Area",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245476",
    },
    {
      lookupValue: "ElectricDryerHookup",
      lookupDisplayName: "Electric Dryer Hookup",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Electric+Dryer+Hookup",
    },
    {
      lookupValue: "GasDryerHookup",
      lookupDisplayName: "Gas Dryer Hookup",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Dryer+Hookup",
    },
    {
      lookupValue: "InBasement",
      lookupDisplayName: "In Basement",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Basement",
    },
    {
      lookupValue: "InBathroom",
      lookupDisplayName: "In Bathroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Bathroom",
    },
    {
      lookupValue: "InCarport",
      lookupDisplayName: "In Carport",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Carport",
    },
    {
      lookupValue: "InGarage",
      lookupDisplayName: "In Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Garage",
    },
    {
      lookupValue: "InHall",
      lookupDisplayName: "In Hall",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Hall",
    },
    {
      lookupValue: "InKitchen",
      lookupDisplayName: "In Kitchen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Kitchen",
    },
    {
      lookupValue: "Inside",
      lookupDisplayName: "Inside",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Inside",
    },
    {
      lookupValue: "InUnit",
      lookupDisplayName: "In Unit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Unit",
    },
    {
      lookupValue: "LaundryChute",
      lookupDisplayName: "Laundry Chute",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Laundry+Chute",
    },
    {
      lookupValue: "LaundryCloset",
      lookupDisplayName: "Laundry Closet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Laundry+Closet",
    },
    {
      lookupValue: "LaundryRoom",
      lookupDisplayName: "Laundry Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Laundry+Room",
    },
    {
      lookupValue: "LowerLevel",
      lookupDisplayName: "Lower Level",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lower+Level",
    },
    {
      lookupValue: "MainLevel",
      lookupDisplayName: "Main Level",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Main+Level",
    },
    {
      lookupValue: "MultipleLocations",
      lookupDisplayName: "Multiple Locations",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Multiple+Locations",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245493",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245494",
    },
    {
      lookupValue: "Outside",
      lookupDisplayName: "Outside",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245495",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245496",
    },
    {
      lookupValue: "Sink",
      lookupDisplayName: "Sink",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sink",
    },
    {
      lookupValue: "UpperLevel",
      lookupDisplayName: "Upper Level",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Upper+Level",
    },
    {
      lookupValue: "WasherHookup",
      lookupDisplayName: "Washer Hookup",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Washer+Hookup",
    },
  ],
  LeaseRenewalCompensation: [
    {
      lookupValue: "CallListingAgent",
      lookupDisplayName: "Call Listing Agent",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245344",
    },
    {
      lookupValue: "CallListingOffice",
      lookupDisplayName: "Call Listing Office",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Call+Listing+Office",
    },
    {
      lookupValue: "CommissionPaidOnTenantPurchase",
      lookupDisplayName: "Commission Paid On Tenant Purchase",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Commission+Paid+On+Tenant+Purchase",
    },
    {
      lookupValue: "NoRenewalCommission",
      lookupDisplayName: "No Renewal Commission",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/No+Renewal+Commission",
    },
    {
      lookupValue: "RenewalCommissionPaid",
      lookupDisplayName: "Renewal Commission Paid",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Renewal+Commission+Paid",
    },
  ],
  LeaseTerm: [
    {
      lookupValue: "MonthToMonth",
      lookupDisplayName: "Month To Month",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Month+To+Month",
    },
    {
      lookupValue: "Negotiable",
      lookupDisplayName: "Negotiable",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Negotiable",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245355",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245356",
    },
    {
      lookupValue: "RenewalOption",
      lookupDisplayName: "Renewal Option",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Renewal+Option",
    },
    {
      lookupValue: "ShortTermLease",
      lookupDisplayName: "Short Term Lease",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Short+Term+Lease",
    },
    {
      lookupValue: "SixMonths",
      lookupDisplayName: "6 Months",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/6+Months",
    },
    {
      lookupValue: "TwelveMonths",
      lookupDisplayName: "12 Months",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/12+Months",
    },
    {
      lookupValue: "TwentyFourMonths",
      lookupDisplayName: "24 Months",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/24+Months",
    },
    {
      lookupValue: "Weekly",
      lookupDisplayName: "Weekly",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245359",
    },
  ],
  Levels: [
    {
      lookupValue: "MultiSplit",
      lookupDisplayName: "Multi/Split",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245361",
    },
    {
      lookupValue: "One",
      lookupDisplayName: "One",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/One",
    },
    {
      lookupValue: "OneAndOneHalf",
      lookupDisplayName: "One and One Half",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/One+and+One+Half",
    },
    {
      lookupValue: "ThreeOrMore",
      lookupDisplayName: "Three Or More",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Three+Or+More",
    },
    {
      lookupValue: "Two",
      lookupDisplayName: "Two",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Two",
    },
  ],
  LinearUnits: [
    {
      lookupValue: "Feet",
      lookupDisplayName: "Feet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Feet",
    },
    {
      lookupValue: "Meters",
      lookupDisplayName: "Meters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Meters",
    },
  ],
  ListAgentDesignation: [
    {
      lookupValue: "AccreditedBuyersRepresentative",
      lookupDisplayName: "Accredited Buyer's Representative / ABR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245436",
    },
    {
      lookupValue: "AccreditedLandConsultant",
      lookupDisplayName: "Accredited Land Consultant / ALC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245437",
    },
    {
      lookupValue: "AtHomeWithDiversity",
      lookupDisplayName: "At Home With Diversity / AHWD",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245438",
    },
    {
      lookupValue: "CertifiedCommercialInvestmentMember",
      lookupDisplayName: "Certified Commercial Investment Member / CCIM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245439",
    },
    {
      lookupValue: "CertifiedDistressedPropertyExpert",
      lookupDisplayName: "Certified Distressed Property Expert / CDPE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245440",
    },
    {
      lookupValue: "CertifiedInternationalPropertySpecialist",
      lookupDisplayName: "Certified International Property Specialist / CIPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245441",
    },
    {
      lookupValue: "CertifiedPropertyManager",
      lookupDisplayName: "Certified Property Manager / CPM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245442",
    },
    {
      lookupValue: "CertifiedRealEstateBrokerageManager",
      lookupDisplayName: "Certified Real Estate Brokerage Manager / CRB",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245443",
    },
    {
      lookupValue: "CertifiedRealEstateTeamSpecialist",
      lookupDisplayName: "Certified Real Estate Team Specialist / C-RETS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245444",
    },
    {
      lookupValue: "CertifiedResidentialSpecialist",
      lookupDisplayName: "Certified Residential Specialist / CRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245445",
    },
    {
      lookupValue: "CounselorOfRealEstate",
      lookupDisplayName: "Counselor of Real Estate / CRE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245446",
    },
    {
      lookupValue: "ePRO",
      lookupDisplayName: "e-PRO",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245447",
    },
    {
      lookupValue: "GeneralAccreditedAppraiser",
      lookupDisplayName: "General Accredited Appraiser / GAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245448",
    },
    {
      lookupValue: "GraduateRealtorInstitute",
      lookupDisplayName: "Graduate, REALTOR Institute / GRI",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245449",
    },
    {
      lookupValue: "MilitaryRelocationProfessional",
      lookupDisplayName: "Military Relocation Professional / MRP",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245450",
    },
    {
      lookupValue: "NARsGreenDesignation",
      lookupDisplayName: "NAR's Green Designation / GREEN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245451",
    },
    {
      lookupValue: "PerformanceManagementNetwork",
      lookupDisplayName: "Performance Management Network / PMN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245452",
    },
    {
      lookupValue: "PricingStrategyAdvisor",
      lookupDisplayName: "Pricing Strategy Advisor / PSA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245453",
    },
    {
      lookupValue: "RealEstateNegotiationExpert",
      lookupDisplayName: "Real Estate Negotiation Expert / RENE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245454",
    },
    {
      lookupValue: "RealtorAssociationCertifiedExecutive",
      lookupDisplayName: "REALTOR Association Certified Executive / RCE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245455",
    },
    {
      lookupValue: "ResidentialAccreditedAppraiser",
      lookupDisplayName: "Residential Accredited Appraiser / RAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245456",
    },
    {
      lookupValue: "ResortAndSecondHomePropertySpecialist",
      lookupDisplayName: "Resort & Second-Home Property Specialist / RSPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245457",
    },
    {
      lookupValue: "SellerRepresentativeSpecialist",
      lookupDisplayName: "Seller Representative Specialist / SRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245458",
    },
    {
      lookupValue: "SeniorsRealEstateSpecialist",
      lookupDisplayName: "Seniors Real Estate Specialist / SRES",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245459",
    },
    {
      lookupValue: "ShortSalesAndForeclosureResource",
      lookupDisplayName: "Short Sales & Foreclosure Resource / SFR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245460",
    },
    {
      lookupValue: "SocietyOfIndustrialAndOfficeRealtors",
      lookupDisplayName: "Society of Industrial and Office REALTORS / SIOR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245461",
    },
    {
      lookupValue: "TransnationalReferralCertification",
      lookupDisplayName: "Transnational Referral Certification / TRC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245462",
    },
  ],
  ListingAgreement: [
    {
      lookupValue: "ExclusiveAgency",
      lookupDisplayName: "Exclusive Agency",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Exclusive+Agency",
    },
    {
      lookupValue: "ExclusiveRightToLease",
      lookupDisplayName: "Exclusive Right To Lease",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Exclusive+Right+To+Lease",
    },
    {
      lookupValue: "ExclusiveRightToSell",
      lookupDisplayName: "Exclusive Right To Sell",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Exclusive+Right+To+Sell",
    },
    {
      lookupValue: "ExclusiveRightWithException",
      lookupDisplayName: "Exclusive Right With Exception",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Exclusive+Right+With+Exception",
    },
    {
      lookupValue: "Net",
      lookupDisplayName: "Net",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245374",
    },
    {
      lookupValue: "Open",
      lookupDisplayName: "Open",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open",
    },
    {
      lookupValue: "Probate",
      lookupDisplayName: "Probate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Probate",
    },
  ],
  ListingService: [
    {
      lookupValue: "EntryOnly",
      lookupDisplayName: "Entry Only",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Entry+Only",
    },
    {
      lookupValue: "FullService",
      lookupDisplayName: "Full Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Full+Service",
    },
    {
      lookupValue: "LimitedService",
      lookupDisplayName: "Limited Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Limited+Service",
    },
  ],
  ListingTerms: [
    {
      lookupValue: "AllInclusiveTrustDeed",
      lookupDisplayName: "All Inclusive Trust Deed",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/All+Inclusive+Trust+Deed",
    },
    {
      lookupValue: "Assumable",
      lookupDisplayName: "Assumable",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245384",
    },
    {
      lookupValue: "Cash",
      lookupDisplayName: "Cash",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245385",
    },
    {
      lookupValue: "Contract",
      lookupDisplayName: "Contract",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245386",
    },
    {
      lookupValue: "Conventional",
      lookupDisplayName: "Conventional",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245387",
    },
    {
      lookupValue: "Exchange1031",
      lookupDisplayName: "1031 Exchange",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/1031+Exchange",
    },
    {
      lookupValue: "ExistingBonds",
      lookupDisplayName: "Existing Bonds",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Existing+Bonds",
    },
    {
      lookupValue: "Fha",
      lookupDisplayName: "FHA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245389",
    },
    {
      lookupValue: "LandUseFee",
      lookupDisplayName: "Land Use Fee",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Land+Use+Fee",
    },
    {
      lookupValue: "LeaseBack",
      lookupDisplayName: "Lease Back",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lease+Back",
    },
    {
      lookupValue: "LeaseOption",
      lookupDisplayName: "Lease Option",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lease+Option",
    },
    {
      lookupValue: "LeasePurchase",
      lookupDisplayName: "Lease Purchase",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lease+Purchase",
    },
    {
      lookupValue: "LienRelease",
      lookupDisplayName: "Lien Release",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lien+Release",
    },
    {
      lookupValue: "OwnerMayCarry",
      lookupDisplayName: "Owner May Carry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Owner+May+Carry",
    },
    {
      lookupValue: "OwnerPayPoints",
      lookupDisplayName: "Owner Pay Points",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Owner+Pay+Points",
    },
    {
      lookupValue: "OwnerWillCarry",
      lookupDisplayName: "Owner Will Carry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Owner+Will+Carry",
    },
    {
      lookupValue: "PrivateFinancingAvailable",
      lookupDisplayName: "Private Financing Available",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Private+Financing+Available",
    },
    {
      lookupValue: "RelocationProperty",
      lookupDisplayName: "Relocation Property",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Relocation+Property",
    },
    {
      lookupValue: "SellerEquityShare",
      lookupDisplayName: "Seller Equity Share",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Seller+Equity+Share",
    },
    {
      lookupValue: "SpecialFunding",
      lookupDisplayName: "Special Funding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Special+Funding",
    },
    {
      lookupValue: "Submit",
      lookupDisplayName: "Submit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Submit",
    },
    {
      lookupValue: "Trade",
      lookupDisplayName: "Trade",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Trade",
    },
    {
      lookupValue: "TrustConveyance",
      lookupDisplayName: "Trust Conveyance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Trust+Conveyance",
    },
    {
      lookupValue: "TrustDeed",
      lookupDisplayName: "Trust Deed",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245405",
    },
    {
      lookupValue: "UsdaLoan",
      lookupDisplayName: "USDA Loan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/USDA+Loan",
    },
    {
      lookupValue: "VaLoan",
      lookupDisplayName: "VA Loan",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VA+Loan",
    },
  ],
  LockBoxType: [
    {
      lookupValue: "CallListingOffice",
      lookupDisplayName: "Call Listing Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245409",
    },
    {
      lookupValue: "CallSellerDirect",
      lookupDisplayName: "Call Seller Direct",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Call+Seller+Direct",
    },
    {
      lookupValue: "Combo",
      lookupDisplayName: "Combo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Combo",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245412",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245416",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245413",
    },
    {
      lookupValue: "Sentrilock",
      lookupDisplayName: "SentriLock",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SentriLock",
    },
    {
      lookupValue: "Supra",
      lookupDisplayName: "Supra",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Supra",
    },
  ],
  LotDimensionsSource: [
    {
      lookupValue: "Appraiser",
      lookupDisplayName: "Appraiser",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245464",
    },
    {
      lookupValue: "Assessor",
      lookupDisplayName: "Assessor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245465",
    },
    {
      lookupValue: "Builder",
      lookupDisplayName: "Builder",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245466",
    },
    {
      lookupValue: "Estimated",
      lookupDisplayName: "Estimated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245467",
    },
    {
      lookupValue: "GisCalculated",
      lookupDisplayName: "GIS Calculated",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GIS+Calculated",
    },
    {
      lookupValue: "Measured",
      lookupDisplayName: "Measured",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Measured",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245470",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245471",
    },
    {
      lookupValue: "PublicRecords",
      lookupDisplayName: "Public Records",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245472",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245473",
    },
    {
      lookupValue: "Survey",
      lookupDisplayName: "Survey",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Survey",
    },
  ],
  LotFeatures: [
    {
      lookupValue: "Agricultural",
      lookupDisplayName: "Agricultural",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245501",
    },
    {
      lookupValue: "BackYard",
      lookupDisplayName: "Back Yard",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245502",
    },
    {
      lookupValue: "Bluff",
      lookupDisplayName: "Bluff",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bluff",
    },
    {
      lookupValue: "CityLot",
      lookupDisplayName: "City Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/City+Lot",
    },
    {
      lookupValue: "Cleared",
      lookupDisplayName: "Cleared",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245505",
    },
    {
      lookupValue: "CloseToClubhouse",
      lookupDisplayName: "Close to Clubhouse",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Close+to+Clubhouse",
    },
    {
      lookupValue: "CornerLot",
      lookupDisplayName: "Corner Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Corner+Lot",
    },
    {
      lookupValue: "CornersMarked",
      lookupDisplayName: "Corners Marked",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Corners+Marked",
    },
    {
      lookupValue: "CulDeSac",
      lookupDisplayName: "Cul-De-Sac",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cul-De-Sac",
    },
    {
      lookupValue: "DesertBack",
      lookupDisplayName: "Desert Back",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Desert+Back",
    },
    {
      lookupValue: "DesertFront",
      lookupDisplayName: "Desert Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Desert+Front",
    },
    {
      lookupValue: "Farm",
      lookupDisplayName: "Farm",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245512",
    },
    {
      lookupValue: "FewTrees",
      lookupDisplayName: "Few Trees",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Few+Trees",
    },
    {
      lookupValue: "FlagLot",
      lookupDisplayName: "Flag Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Flag+Lot",
    },
    {
      lookupValue: "FrontYard",
      lookupDisplayName: "Front Yard",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245515",
    },
    {
      lookupValue: "Garden",
      lookupDisplayName: "Garden",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245516",
    },
    {
      lookupValue: "GentleSloping",
      lookupDisplayName: "Gentle Sloping",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gentle+Sloping",
    },
    {
      lookupValue: "Greenbelt",
      lookupDisplayName: "Greenbelt",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Greenbelt",
    },
    {
      lookupValue: "InteriorLot",
      lookupDisplayName: "Interior Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Interior+Lot",
    },
    {
      lookupValue: "IrregularLot",
      lookupDisplayName: "Irregular Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Irregular+Lot",
    },
    {
      lookupValue: "Landscaped",
      lookupDisplayName: "Landscaped",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Landscaped",
    },
    {
      lookupValue: "Level",
      lookupDisplayName: "Level",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Level",
    },
    {
      lookupValue: "ManyTrees",
      lookupDisplayName: "Many Trees",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Many+Trees",
    },
    {
      lookupValue: "Meadow",
      lookupDisplayName: "Meadow",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Meadow",
    },
    {
      lookupValue: "NativePlants",
      lookupDisplayName: "Native Plants",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Native+Plants",
    },
    {
      lookupValue: "NearGolfCourse",
      lookupDisplayName: "Near Golf Course",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Near+Golf+Course",
    },
    {
      lookupValue: "NearPublicTransit",
      lookupDisplayName: "Near Public Transit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Near+Public+Transit",
    },
    {
      lookupValue: "OnGolfCourse",
      lookupDisplayName: "On Golf Course",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/On+Golf+Course",
    },
    {
      lookupValue: "OpenLot",
      lookupDisplayName: "Open Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Open+Lot",
    },
    {
      lookupValue: "Orchards",
      lookupDisplayName: "Orchard(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245530",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245531",
    },
    {
      lookupValue: "Pasture",
      lookupDisplayName: "Pasture",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245532",
    },
    {
      lookupValue: "Paved",
      lookupDisplayName: "Paved",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245533",
    },
    {
      lookupValue: "PieShapedLot",
      lookupDisplayName: "Pie Shaped Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pie+Shaped+Lot",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245535",
    },
    {
      lookupValue: "RectangularLot",
      lookupDisplayName: "Rectangular Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rectangular+Lot",
    },
    {
      lookupValue: "RockOutcropping",
      lookupDisplayName: "Rock Outcropping",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rock+Outcropping",
    },
    {
      lookupValue: "RollingSlope",
      lookupDisplayName: "Rolling Slope",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rolling+Slope",
    },
    {
      lookupValue: "Secluded",
      lookupDisplayName: "Secluded",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Secluded",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245540",
    },
    {
      lookupValue: "Sloped",
      lookupDisplayName: "Sloped",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sloped",
    },
    {
      lookupValue: "SlopedDown",
      lookupDisplayName: "Sloped Down",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sloped+Down",
    },
    {
      lookupValue: "SlopedUp",
      lookupDisplayName: "Sloped Up",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sloped+Up",
    },
    {
      lookupValue: "SplitPossible",
      lookupDisplayName: "Split Possible",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Split+Possible",
    },
    {
      lookupValue: "SprinklersInFront",
      lookupDisplayName: "Sprinklers In Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sprinklers+In+Front",
    },
    {
      lookupValue: "SprinklersInRear",
      lookupDisplayName: "Sprinklers In Rear",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sprinklers+In+Rear",
    },
    {
      lookupValue: "SteepSlope",
      lookupDisplayName: "Steep Slope",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Steep+Slope",
    },
    {
      lookupValue: "Subdivided",
      lookupDisplayName: "Subdivided",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Subdivided",
    },
    {
      lookupValue: "Views",
      lookupDisplayName: "Views",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Views",
    },
    {
      lookupValue: "Waterfall",
      lookupDisplayName: "Waterfall",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Waterfall",
    },
    {
      lookupValue: "Waterfront",
      lookupDisplayName: "Waterfront",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245551",
    },
    {
      lookupValue: "Wetlands",
      lookupDisplayName: "Wetlands",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wetlands",
    },
    {
      lookupValue: "Wooded",
      lookupDisplayName: "Wooded",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245553",
    },
    {
      lookupValue: "ZeroLotLine",
      lookupDisplayName: "Zero Lot Line",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Zero+Lot+Line",
    },
  ],
  LotSizeSource: [
    {
      lookupValue: "Appraiser",
      lookupDisplayName: "Appraiser",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245418",
    },
    {
      lookupValue: "Assessor",
      lookupDisplayName: "Assessor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245419",
    },
    {
      lookupValue: "Builder",
      lookupDisplayName: "Builder",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245420",
    },
    {
      lookupValue: "Estimated",
      lookupDisplayName: "Estimated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245421",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245422",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245423",
    },
    {
      lookupValue: "Plans",
      lookupDisplayName: "Plans",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245424",
    },
    {
      lookupValue: "PublicRecords",
      lookupDisplayName: "Public Records",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245425",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245426",
    },
  ],
  LotSizeUnits: [
    {
      lookupValue: "Acres",
      lookupDisplayName: "Acres",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Acres",
    },
    {
      lookupValue: "SquareFeet",
      lookupDisplayName: "Square Feet",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245429",
    },
    {
      lookupValue: "SquareMeters",
      lookupDisplayName: "Square Meters",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245430",
    },
  ],
  MediaCategory: [
    {
      lookupValue: "AgentPhoto",
      lookupDisplayName: "Agent Photo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Agent+Photo",
    },
    {
      lookupValue: "BrandedVirtualTour",
      lookupDisplayName: "Branded Virtual Tour",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Branded+Virtual+Tour",
    },
    {
      lookupValue: "Document",
      lookupDisplayName: "Document",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Document",
    },
    {
      lookupValue: "FloorPlan",
      lookupDisplayName: "Floor Plan",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245560",
    },
    {
      lookupValue: "OfficeLogo",
      lookupDisplayName: "Office Logo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Office+Logo",
    },
    {
      lookupValue: "OfficePhoto",
      lookupDisplayName: "Office Photo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Office+Photo",
    },
    {
      lookupValue: "Photo",
      lookupDisplayName: "Photo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Photo",
    },
    {
      lookupValue: "UnbrandedVirtualTour",
      lookupDisplayName: "Unbranded Virtual Tour",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Unbranded+Virtual+Tour",
    },
    {
      lookupValue: "Video",
      lookupDisplayName: "Video",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245565",
    },
  ],
  MediaType: [
    {
      lookupValue: "Doc",
      lookupDisplayName: "doc",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/doc",
    },
    {
      lookupValue: "Docx",
      lookupDisplayName: "docx",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/docx",
    },
    {
      lookupValue: "Gif",
      lookupDisplayName: "gif",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/gif",
    },
    {
      lookupValue: "Jpeg",
      lookupDisplayName: "jpeg",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/jpeg",
    },
    {
      lookupValue: "Mov",
      lookupDisplayName: "mov",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/mov",
    },
    {
      lookupValue: "Mp4",
      lookupDisplayName: "mp4",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/mp4",
    },
    {
      lookupValue: "Mpeg",
      lookupDisplayName: "mpeg",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/mpeg",
    },
    {
      lookupValue: "Pdf",
      lookupDisplayName: "pdf",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/pdf",
    },
    {
      lookupValue: "Png",
      lookupDisplayName: "png",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/png",
    },
    {
      lookupValue: "Quicktime",
      lookupDisplayName: "quicktime",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/quicktime",
    },
    {
      lookupValue: "Rtf",
      lookupDisplayName: "rtf",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/rtf",
    },
    {
      lookupValue: "Tiff",
      lookupDisplayName: "tiff",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/tiff",
    },
    {
      lookupValue: "Txt",
      lookupDisplayName: "txt",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/txt",
    },
    {
      lookupValue: "Wmv",
      lookupDisplayName: "wmv",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/wmv",
    },
    {
      lookupValue: "Xls",
      lookupDisplayName: "xls",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/xls",
    },
    {
      lookupValue: "Xlsx",
      lookupDisplayName: "xlsx",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/xlsx",
    },
  ],
  MemberDesignation: [
    {
      lookupValue: "AccreditedBuyersRepresentative",
      lookupDisplayName: "Accredited Buyer's Representative / ABR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245602",
    },
    {
      lookupValue: "AccreditedLandConsultant",
      lookupDisplayName: "Accredited Land Consultant / ALC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245603",
    },
    {
      lookupValue: "AtHomeWithDiversity",
      lookupDisplayName: "At Home With Diversity / AHWD",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245604",
    },
    {
      lookupValue: "CertifiedCommercialInvestmentMember",
      lookupDisplayName: "Certified Commercial Investment Member / CCIM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245605",
    },
    {
      lookupValue: "CertifiedDistressedPropertyExpert",
      lookupDisplayName: "Certified Distressed Property Expert / CDPE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245606",
    },
    {
      lookupValue: "CertifiedInternationalPropertySpecialist",
      lookupDisplayName: "Certified International Property Specialist / CIPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245607",
    },
    {
      lookupValue: "CertifiedPropertyManager",
      lookupDisplayName: "Certified Property Manager / CPM",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245608",
    },
    {
      lookupValue: "CertifiedRealEstateBrokerageManager",
      lookupDisplayName: "Certified Real Estate Brokerage Manager / CRB",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245609",
    },
    {
      lookupValue: "CertifiedRealEstateTeamSpecialist",
      lookupDisplayName: "Certified Real Estate Team Specialist / C-RETS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245610",
    },
    {
      lookupValue: "CertifiedResidentialSpecialist",
      lookupDisplayName: "Certified Residential Specialist / CRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245611",
    },
    {
      lookupValue: "CounselorOfRealEstate",
      lookupDisplayName: "Counselor of Real Estate / CRE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245612",
    },
    {
      lookupValue: "ePRO",
      lookupDisplayName: "e-PRO",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245613",
    },
    {
      lookupValue: "GeneralAccreditedAppraiser",
      lookupDisplayName: "General Accredited Appraiser / GAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245614",
    },
    {
      lookupValue: "GraduateRealtorInstitute",
      lookupDisplayName: "Graduate, REALTOR Institute / GRI",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245615",
    },
    {
      lookupValue: "MilitaryRelocationProfessional",
      lookupDisplayName: "Military Relocation Professional / MRP",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245616",
    },
    {
      lookupValue: "NARsGreenDesignation",
      lookupDisplayName: "NAR's Green Designation / GREEN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245617",
    },
    {
      lookupValue: "PerformanceManagementNetwork",
      lookupDisplayName: "Performance Management Network / PMN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245618",
    },
    {
      lookupValue: "PricingStrategyAdvisor",
      lookupDisplayName: "Pricing Strategy Advisor / PSA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245619",
    },
    {
      lookupValue: "RealEstateNegotiationExpert",
      lookupDisplayName: "Real Estate Negotiation Expert / RENE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245620",
    },
    {
      lookupValue: "RealtorAssociationCertifiedExecutive",
      lookupDisplayName: "REALTOR Association Certified Executive / RCE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245621",
    },
    {
      lookupValue: "ResidentialAccreditedAppraiser",
      lookupDisplayName: "Residential Accredited Appraiser / RAA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245622",
    },
    {
      lookupValue: "ResortAndSecondHomePropertySpecialist",
      lookupDisplayName: "Resort & Second-Home Property Specialist / RSPS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245623",
    },
    {
      lookupValue: "SellerRepresentativeSpecialist",
      lookupDisplayName: "Seller Representative Specialist / SRS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245624",
    },
    {
      lookupValue: "SeniorsRealEstateSpecialist",
      lookupDisplayName: "Seniors Real Estate Specialist / SRES",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245625",
    },
    {
      lookupValue: "ShortSalesAndForeclosureResource",
      lookupDisplayName: "Short Sales & Foreclosure Resource / SFR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245626",
    },
    {
      lookupValue: "SocietyOfIndustrialAndOfficeRealtors",
      lookupDisplayName: "Society of Industrial and Office REALTORS / SIOR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245627",
    },
    {
      lookupValue: "TransnationalReferralCertification",
      lookupDisplayName: "Transnational Referral Certification / TRC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245628",
    },
  ],
  MemberOtherPhoneType: [
    {
      lookupValue: "Direct",
      lookupDisplayName: "Direct",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Direct",
    },
    {
      lookupValue: "Fax",
      lookupDisplayName: "Fax",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fax",
    },
    {
      lookupValue: "First",
      lookupDisplayName: "First",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/First",
    },
    {
      lookupValue: "Home",
      lookupDisplayName: "Home",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Home",
    },
    {
      lookupValue: "Mobile",
      lookupDisplayName: "Mobile",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mobile",
    },
    {
      lookupValue: "Modem",
      lookupDisplayName: "Modem",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Modem",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245636",
    },
    {
      lookupValue: "Pager",
      lookupDisplayName: "Pager",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pager",
    },
    {
      lookupValue: "Preferred",
      lookupDisplayName: "Preferred",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Preferred",
    },
    {
      lookupValue: "Second",
      lookupDisplayName: "Second",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Second",
    },
    {
      lookupValue: "Sms",
      lookupDisplayName: "SMS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SMS",
    },
    {
      lookupValue: "Third",
      lookupDisplayName: "Third",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Third",
    },
    {
      lookupValue: "TollFree",
      lookupDisplayName: "Toll Free",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Toll+Free",
    },
    {
      lookupValue: "Voicemail",
      lookupDisplayName: "Voicemail",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Voicemail",
    },
  ],
  MemberStatus: [
    {
      lookupValue: "Active",
      lookupDisplayName: "Active",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245584",
    },
    {
      lookupValue: "Inactive",
      lookupDisplayName: "Inactive",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Inactive",
    },
  ],
  MemberType: [
    {
      lookupValue: "Assistant",
      lookupDisplayName: "Assistant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Assistant",
    },
    {
      lookupValue: "AssociationStaff",
      lookupDisplayName: "Association Staff",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Association+Staff",
    },
    {
      lookupValue: "DesignatedRealtorAppraiser",
      lookupDisplayName: "Designated REALTOR Appraiser",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Designated+REALTOR+Appraiser",
    },
    {
      lookupValue: "DesignatedRealtorParticipant",
      lookupDisplayName: "Designated REALTOR Participant",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Designated+REALTOR+Participant",
    },
    {
      lookupValue: "LicensedAssistant",
      lookupDisplayName: "Licensed Assistant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Licensed+Assistant",
    },
    {
      lookupValue: "MlsOnlyAppraiser",
      lookupDisplayName: "MLS Only Appraiser",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS+Only+Appraiser",
    },
    {
      lookupValue: "MlsOnlyBroker",
      lookupDisplayName: "MLS Only Broker",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS+Only+Broker",
    },
    {
      lookupValue: "MlsOnlySalesperson",
      lookupDisplayName: "MLS Only Salesperson",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS+Only+Salesperson",
    },
    {
      lookupValue: "MlsStaff",
      lookupDisplayName: "MLS Staff",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS+Staff",
    },
    {
      lookupValue: "NonMemberVendor",
      lookupDisplayName: "Non Member/Vendor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245594",
    },
    {
      lookupValue: "OfficeManager",
      lookupDisplayName: "Office Manager",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Office+Manager",
    },
    {
      lookupValue: "RealtorAppraiser",
      lookupDisplayName: "REALTOR Appraiser",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/REALTOR+Appraiser",
    },
    {
      lookupValue: "RealtorSalesperson",
      lookupDisplayName: "REALTOR Salesperson",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/REALTOR+Salesperson",
    },
    {
      lookupValue: "UnlicensedAssistant",
      lookupDisplayName: "Unlicensed Assistant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unlicensed+Assistant",
    },
  ],
  NotedBy: [
    {
      lookupValue: "Agent",
      lookupDisplayName: "Agent",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246727",
    },
    {
      lookupValue: "Contact",
      lookupDisplayName: "Contact",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Contact",
    },
  ],
  ObjectIdType: [
    {
      lookupValue: "Listingid",
      lookupDisplayName: "ListingId",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245795",
    },
    {
      lookupValue: "Listingkey",
      lookupDisplayName: "ListingKey",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245796",
    },
    {
      lookupValue: "Listingkeynumeric",
      lookupDisplayName: "ListingKeyNumeric",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245797",
    },
    {
      lookupValue: "Openhouseid",
      lookupDisplayName: "OpenHouseId",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245798",
    },
    {
      lookupValue: "Openhousekey",
      lookupDisplayName: "OpenHouseKey",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245799",
    },
    {
      lookupValue: "Openhousekeynumeric",
      lookupDisplayName: "OpenHouseKeyNumeric",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245800",
    },
    {
      lookupValue: "Parcelnumber",
      lookupDisplayName: "ParcelNumber",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245801",
    },
    {
      lookupValue: "Puid",
      lookupDisplayName: "PUID",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/PUID",
    },
    {
      lookupValue: "Savedsearchkey",
      lookupDisplayName: "SavedSearchKey",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245803",
    },
    {
      lookupValue: "Savedsearchkeynumeric",
      lookupDisplayName: "SavedSearchKeyNumeric",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245804",
    },
    {
      lookupValue: "Unique",
      lookupDisplayName: "Unique",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unique",
    },
  ],
  ObjectType: [
    {
      lookupValue: "Document",
      lookupDisplayName: "Document",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245812",
    },
    {
      lookupValue: "Listing",
      lookupDisplayName: "Listing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Listing",
    },
    {
      lookupValue: "OpenHouse",
      lookupDisplayName: "Open House",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245808",
    },
    {
      lookupValue: "Photo",
      lookupDisplayName: "Photo",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245811",
    },
    {
      lookupValue: "Property",
      lookupDisplayName: "Property",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245809",
    },
    {
      lookupValue: "SavedSearch",
      lookupDisplayName: "Saved Search",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245810",
    },
    {
      lookupValue: "VirtualTour",
      lookupDisplayName: "Virtual Tour",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245813",
    },
  ],
  OccupantType: [
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245646",
    },
    {
      lookupValue: "Tenant",
      lookupDisplayName: "Tenant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tenant",
    },
    {
      lookupValue: "Vacant",
      lookupDisplayName: "Vacant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vacant",
    },
  ],
  OfficeBranchType: [
    {
      lookupValue: "Branch",
      lookupDisplayName: "Branch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Branch",
    },
    {
      lookupValue: "Main",
      lookupDisplayName: "Main",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Main",
    },
    {
      lookupValue: "StandAlone",
      lookupDisplayName: "Stand Alone",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stand+Alone",
    },
  ],
  OfficeStatus: [
    {
      lookupValue: "Active",
      lookupDisplayName: "Active",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245650",
    },
    {
      lookupValue: "Inactive",
      lookupDisplayName: "Inactive",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245651",
    },
  ],
  OfficeType: [
    {
      lookupValue: "Affiliate",
      lookupDisplayName: "Affiliate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Affiliate",
    },
    {
      lookupValue: "Appraiser",
      lookupDisplayName: "Appraiser",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245654",
    },
    {
      lookupValue: "Association",
      lookupDisplayName: "Association",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Association",
    },
    {
      lookupValue: "Mls",
      lookupDisplayName: "MLS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS",
    },
    {
      lookupValue: "MlsOnlyBranch",
      lookupDisplayName: "MLS Only Branch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS+Only+Branch",
    },
    {
      lookupValue: "MlsOnlyFirm",
      lookupDisplayName: "MLS Only Firm",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS+Only+Firm",
    },
    {
      lookupValue: "MlsOnlyOffice",
      lookupDisplayName: "MLS Only Office",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MLS+Only+Office",
    },
    {
      lookupValue: "NonMemberVendor",
      lookupDisplayName: "Non Member/Vendor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245660",
    },
    {
      lookupValue: "RealtorBranchOffice",
      lookupDisplayName: "Realtor Branch Office",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Realtor+Branch+Office",
    },
    {
      lookupValue: "RealtorFirm",
      lookupDisplayName: "Realtor Firm",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Realtor+Firm",
    },
    {
      lookupValue: "RealtorOffice",
      lookupDisplayName: "Realtor Office",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Realtor+Office",
    },
  ],
  OpenHouseStatus: [
    {
      lookupValue: "Active",
      lookupDisplayName: "Active",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245665",
    },
    {
      lookupValue: "Canceled",
      lookupDisplayName: "Canceled",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245666",
    },
    {
      lookupValue: "Ended",
      lookupDisplayName: "Ended",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ended",
    },
  ],
  OpenHouseType: [
    {
      lookupValue: "Broker",
      lookupDisplayName: "Broker",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Broker",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245670",
    },
    {
      lookupValue: "Public",
      lookupDisplayName: "Public",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Public",
    },
  ],
  OperatingExpenseIncludes: [
    {
      lookupValue: "Accounting",
      lookupDisplayName: "Accounting",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245673",
    },
    {
      lookupValue: "Advertising",
      lookupDisplayName: "Advertising",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245674",
    },
    {
      lookupValue: "Association",
      lookupDisplayName: "Association",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245675",
    },
    {
      lookupValue: "CableTv",
      lookupDisplayName: "Cable TV",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245676",
    },
    {
      lookupValue: "CapitalImprovements",
      lookupDisplayName: "Capital Improvements",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Capital+Improvements",
    },
    {
      lookupValue: "Depreciation",
      lookupDisplayName: "Depreciation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Depreciation",
    },
    {
      lookupValue: "EquipmentRental",
      lookupDisplayName: "Equipment Rental",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Equipment+Rental",
    },
    {
      lookupValue: "Fuel",
      lookupDisplayName: "Fuel",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fuel",
    },
    {
      lookupValue: "FurnitureReplacement",
      lookupDisplayName: "Furniture Replacement",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Furniture+Replacement",
    },
    {
      lookupValue: "Gardener",
      lookupDisplayName: "Gardener",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gardener",
    },
    {
      lookupValue: "Insurance",
      lookupDisplayName: "Insurance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245683",
    },
    {
      lookupValue: "Legal",
      lookupDisplayName: "Legal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Legal",
    },
    {
      lookupValue: "Licenses",
      lookupDisplayName: "Licenses",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Licenses",
    },
    {
      lookupValue: "Maintenance",
      lookupDisplayName: "Maintenance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Maintenance",
    },
    {
      lookupValue: "MaintenanceGrounds",
      lookupDisplayName: "Maintenance Grounds",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245687",
    },
    {
      lookupValue: "MaintenanceStructure",
      lookupDisplayName: "Maintenance Structure",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Maintenance+Structure",
    },
    {
      lookupValue: "Manager",
      lookupDisplayName: "Manager",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manager",
    },
    {
      lookupValue: "MortgageLoans",
      lookupDisplayName: "Mortgage/Loans",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245690",
    },
    {
      lookupValue: "NewTax",
      lookupDisplayName: "New Tax",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/New+Tax",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245692",
    },
    {
      lookupValue: "Parking",
      lookupDisplayName: "Parking",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245693",
    },
    {
      lookupValue: "PestControl",
      lookupDisplayName: "Pest Control",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245694",
    },
    {
      lookupValue: "PoolSpa",
      lookupDisplayName: "Pool/Spa",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245695",
    },
    {
      lookupValue: "ProfessionalManagement",
      lookupDisplayName: "Professional Management",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Professional+Management",
    },
    {
      lookupValue: "Security",
      lookupDisplayName: "Security",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245697",
    },
    {
      lookupValue: "SnowRemoval",
      lookupDisplayName: "Snow Removal",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245698",
    },
    {
      lookupValue: "Staff",
      lookupDisplayName: "Staff",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Staff",
    },
    {
      lookupValue: "Supplies",
      lookupDisplayName: "Supplies",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Supplies",
    },
    {
      lookupValue: "Trash",
      lookupDisplayName: "Trash",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245701",
    },
    {
      lookupValue: "Utilities",
      lookupDisplayName: "Utilities",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245702",
    },
    {
      lookupValue: "VacancyAllowance",
      lookupDisplayName: "Vacancy Allowance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vacancy+Allowance",
    },
    {
      lookupValue: "WaterSewer",
      lookupDisplayName: "Water/Sewer",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245704",
    },
    {
      lookupValue: "WorkmansCompensation",
      lookupDisplayName: "Workmans Compensation",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Workmans+Compensation",
    },
  ],
  OtherEquipment: [
    {
      lookupValue: "AirPurifier",
      lookupDisplayName: "Air Purifier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Air+Purifier",
    },
    {
      lookupValue: "CallListingAgent",
      lookupDisplayName: "Call Listing Agent",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245816",
    },
    {
      lookupValue: "Compressor",
      lookupDisplayName: "Compressor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Compressor",
    },
    {
      lookupValue: "DcWellPump",
      lookupDisplayName: "DC Well Pump",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DC+Well+Pump",
    },
    {
      lookupValue: "Dehumidifier",
      lookupDisplayName: "Dehumidifier",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dehumidifier",
    },
    {
      lookupValue: "FarmEquipment",
      lookupDisplayName: "Farm Equipment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Farm+Equipment",
    },
    {
      lookupValue: "FuelTanks",
      lookupDisplayName: "Fuel Tank(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245821",
    },
    {
      lookupValue: "Generator",
      lookupDisplayName: "Generator",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245822",
    },
    {
      lookupValue: "HomeTheater",
      lookupDisplayName: "Home Theater",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Home+Theater",
    },
    {
      lookupValue: "Intercom",
      lookupDisplayName: "Intercom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Intercom",
    },
    {
      lookupValue: "IrrigationEquipment",
      lookupDisplayName: "Irrigation Equipment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Irrigation+Equipment",
    },
    {
      lookupValue: "ListAvailable",
      lookupDisplayName: "List Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/List+Available",
    },
    {
      lookupValue: "LivestockEquipment",
      lookupDisplayName: "Livestock Equipment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Livestock+Equipment",
    },
    {
      lookupValue: "Negotiable",
      lookupDisplayName: "Negotiable",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245828",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245829",
    },
    {
      lookupValue: "OrchardEquipment",
      lookupDisplayName: "Orchard Equipment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Orchard+Equipment",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245831",
    },
    {
      lookupValue: "RotaryAntenna",
      lookupDisplayName: "Rotary Antenna",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rotary+Antenna",
    },
    {
      lookupValue: "SatelliteDish",
      lookupDisplayName: "Satellite Dish",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Satellite+Dish",
    },
    {
      lookupValue: "TvAntenna",
      lookupDisplayName: "TV Antenna",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TV+Antenna",
    },
    {
      lookupValue: "VariesByUnit",
      lookupDisplayName: "Varies by Unit",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245835",
    },
  ],
  OtherPhoneType: [
    {
      lookupValue: "Direct",
      lookupDisplayName: "Direct",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245746",
    },
    {
      lookupValue: "Fax",
      lookupDisplayName: "Fax",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245747",
    },
    {
      lookupValue: "First",
      lookupDisplayName: "First",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245748",
    },
    {
      lookupValue: "Home",
      lookupDisplayName: "Home",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245749",
    },
    {
      lookupValue: "Mobile",
      lookupDisplayName: "Mobile",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245750",
    },
    {
      lookupValue: "Modem",
      lookupDisplayName: "Modem",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245751",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245752",
    },
    {
      lookupValue: "Pager",
      lookupDisplayName: "Pager",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245753",
    },
    {
      lookupValue: "Preferred",
      lookupDisplayName: "Preferred",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245754",
    },
    {
      lookupValue: "Second",
      lookupDisplayName: "Second",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245755",
    },
    {
      lookupValue: "Sms",
      lookupDisplayName: "SMS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245756",
    },
    {
      lookupValue: "Third",
      lookupDisplayName: "Third",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245757",
    },
    {
      lookupValue: "TollFree",
      lookupDisplayName: "Toll Free",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245758",
    },
    {
      lookupValue: "Voicemail",
      lookupDisplayName: "Voicemail",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245759",
    },
  ],
  OtherStructures: [
    {
      lookupValue: "AirplaneHangar",
      lookupDisplayName: "Airplane Hangar",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Airplane+Hangar",
    },
    {
      lookupValue: "Arena",
      lookupDisplayName: "Arena",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Arena",
    },
    {
      lookupValue: "Barns",
      lookupDisplayName: "Barn(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245763",
    },
    {
      lookupValue: "BoatHouse",
      lookupDisplayName: "Boat House",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Boat+House",
    },
    {
      lookupValue: "Cabana",
      lookupDisplayName: "Cabana",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cabana",
    },
    {
      lookupValue: "Caves",
      lookupDisplayName: "Cave(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245766",
    },
    {
      lookupValue: "Corrals",
      lookupDisplayName: "Corral(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245767",
    },
    {
      lookupValue: "CoveredArena",
      lookupDisplayName: "Covered Arena",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Covered+Arena",
    },
    {
      lookupValue: "Garages",
      lookupDisplayName: "Garage(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245769",
    },
    {
      lookupValue: "Gazebo",
      lookupDisplayName: "Gazebo",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gazebo",
    },
    {
      lookupValue: "GrainStorage",
      lookupDisplayName: "Grain Storage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Grain+Storage",
    },
    {
      lookupValue: "Greenhouse",
      lookupDisplayName: "Greenhouse",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Greenhouse",
    },
    {
      lookupValue: "GuestHouse",
      lookupDisplayName: "Guest House",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Guest+House",
    },
    {
      lookupValue: "KennelDogRun",
      lookupDisplayName: "Kennel/Dog Run",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245774",
    },
    {
      lookupValue: "MobileHome",
      lookupDisplayName: "Mobile Home",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245775",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245776",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245777",
    },
    {
      lookupValue: "Outbuilding",
      lookupDisplayName: "Outbuilding",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Outbuilding",
    },
    {
      lookupValue: "OutdoorKitchen",
      lookupDisplayName: "Outdoor Kitchen",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Outdoor+Kitchen",
    },
    {
      lookupValue: "PackingShed",
      lookupDisplayName: "Packing Shed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Packing+Shed",
    },
    {
      lookupValue: "Pergola",
      lookupDisplayName: "Pergola",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pergola",
    },
    {
      lookupValue: "PoolHouse",
      lookupDisplayName: "Pool House",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pool+House",
    },
    {
      lookupValue: "PoultryCoop",
      lookupDisplayName: "Poultry Coop",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Poultry+Coop",
    },
    {
      lookupValue: "Residence",
      lookupDisplayName: "Residence",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Residence",
    },
    {
      lookupValue: "RvBoatStorage",
      lookupDisplayName: "RV/Boat Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245785",
    },
    {
      lookupValue: "SecondGarage",
      lookupDisplayName: "Second Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Second+Garage",
    },
    {
      lookupValue: "SecondResidence",
      lookupDisplayName: "Second Residence",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Second+Residence",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245788",
    },
    {
      lookupValue: "Sheds",
      lookupDisplayName: "Shed(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245789",
    },
    {
      lookupValue: "Stables",
      lookupDisplayName: "Stable(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245790",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245791",
    },
    {
      lookupValue: "TennisCourts",
      lookupDisplayName: "Tennis Court(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245792",
    },
    {
      lookupValue: "Workshop",
      lookupDisplayName: "Workshop",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245793",
    },
  ],
  OwnerPays: [
    {
      lookupValue: "AllUtilities",
      lookupDisplayName: "All Utilities",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/All+Utilities",
    },
    {
      lookupValue: "AssociationFees",
      lookupDisplayName: "Association Fees",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Association+Fees",
    },
    {
      lookupValue: "CableTv",
      lookupDisplayName: "Cable TV",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245709",
    },
    {
      lookupValue: "CommonAreaMaintenance",
      lookupDisplayName: "Common Area Maintenance",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Common+Area+Maintenance",
    },
    {
      lookupValue: "Electricity",
      lookupDisplayName: "Electricity",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245711",
    },
    {
      lookupValue: "ExteriorMaintenance",
      lookupDisplayName: "Exterior Maintenance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Exterior+Maintenance",
    },
    {
      lookupValue: "Gas",
      lookupDisplayName: "Gas",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245713",
    },
    {
      lookupValue: "GroundsCare",
      lookupDisplayName: "Grounds Care",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Grounds+Care",
    },
    {
      lookupValue: "HotWater",
      lookupDisplayName: "Hot Water",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hot+Water",
    },
    {
      lookupValue: "HvacMaintenance",
      lookupDisplayName: "HVAC Maintenance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HVAC+Maintenance",
    },
    {
      lookupValue: "Insurance",
      lookupDisplayName: "Insurance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245717",
    },
    {
      lookupValue: "JanitorialService",
      lookupDisplayName: "Janitorial Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Janitorial+Service",
    },
    {
      lookupValue: "Management",
      lookupDisplayName: "Management",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Management",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245720",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245721",
    },
    {
      lookupValue: "OtherTax",
      lookupDisplayName: "Other Tax",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Other+Tax",
    },
    {
      lookupValue: "ParkingFee",
      lookupDisplayName: "Parking Fee",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Parking+Fee",
    },
    {
      lookupValue: "PestControl",
      lookupDisplayName: "Pest Control",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245724",
    },
    {
      lookupValue: "PoolMaintenance",
      lookupDisplayName: "Pool Maintenance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pool+Maintenance",
    },
    {
      lookupValue: "Repairs",
      lookupDisplayName: "Repairs",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Repairs",
    },
    {
      lookupValue: "RoofMaintenance",
      lookupDisplayName: "Roof Maintenance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Roof+Maintenance",
    },
    {
      lookupValue: "Security",
      lookupDisplayName: "Security",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245728",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245729",
    },
    {
      lookupValue: "Sewer",
      lookupDisplayName: "Sewer",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245730",
    },
    {
      lookupValue: "SnowRemoval",
      lookupDisplayName: "Snow Removal",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245731",
    },
    {
      lookupValue: "Taxes",
      lookupDisplayName: "Taxes",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Taxes",
    },
    {
      lookupValue: "Telephone",
      lookupDisplayName: "Telephone",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Telephone",
    },
    {
      lookupValue: "TrashCollection",
      lookupDisplayName: "Trash Collection",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Trash+Collection",
    },
    {
      lookupValue: "Water",
      lookupDisplayName: "Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245735",
    },
  ],
  OwnershipType: [
    {
      lookupValue: "Corporation",
      lookupDisplayName: "Corporation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Corporation",
    },
    {
      lookupValue: "Llc",
      lookupDisplayName: "LLC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LLC",
    },
    {
      lookupValue: "Partnership",
      lookupDisplayName: "Partnership",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Partnership",
    },
    {
      lookupValue: "SoleProprietor",
      lookupDisplayName: "Sole Proprietor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sole+Proprietor",
    },
  ],
  ParkingFeatures: [
    {
      lookupValue: "AdditionalParking",
      lookupDisplayName: "Additional Parking",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Additional+Parking",
    },
    {
      lookupValue: "Aggregate",
      lookupDisplayName: "Aggregate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aggregate",
    },
    {
      lookupValue: "AlleyAccess",
      lookupDisplayName: "Alley Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Alley+Access",
    },
    {
      lookupValue: "Asphalt",
      lookupDisplayName: "Asphalt",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245967",
    },
    {
      lookupValue: "Assigned",
      lookupDisplayName: "Assigned",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Assigned",
    },
    {
      lookupValue: "Attached",
      lookupDisplayName: "Attached",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Attached",
    },
    {
      lookupValue: "AttachedCarport",
      lookupDisplayName: "Attached Carport",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Attached+Carport",
    },
    {
      lookupValue: "Basement",
      lookupDisplayName: "Basement",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246035",
    },
    {
      lookupValue: "Boat",
      lookupDisplayName: "Boat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Boat",
    },
    {
      lookupValue: "Carport",
      lookupDisplayName: "Carport",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Carport",
    },
    {
      lookupValue: "CircularDriveway",
      lookupDisplayName: "Circular Driveway",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Circular+Driveway",
    },
    {
      lookupValue: "Common",
      lookupDisplayName: "Common",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Common",
    },
    {
      lookupValue: "CommunityStructure",
      lookupDisplayName: "Community Structure",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Community+Structure",
    },
    {
      lookupValue: "Concrete",
      lookupDisplayName: "Concrete",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245976",
    },
    {
      lookupValue: "ConvertedGarage",
      lookupDisplayName: "Converted Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Converted+Garage",
    },
    {
      lookupValue: "Covered",
      lookupDisplayName: "Covered",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Covered",
    },
    {
      lookupValue: "Deck",
      lookupDisplayName: "Deck",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245979",
    },
    {
      lookupValue: "Deeded",
      lookupDisplayName: "Deeded",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Deeded",
    },
    {
      lookupValue: "Detached",
      lookupDisplayName: "Detached",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Detached",
    },
    {
      lookupValue: "DetachedCarport",
      lookupDisplayName: "Detached Carport",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Detached+Carport",
    },
    {
      lookupValue: "DirectAccess",
      lookupDisplayName: "Direct Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Direct+Access",
    },
    {
      lookupValue: "DriveThrough",
      lookupDisplayName: "Drive Through",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Drive+Through",
    },
    {
      lookupValue: "Driveway",
      lookupDisplayName: "Driveway",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Driveway",
    },
    {
      lookupValue: "ElectricGate",
      lookupDisplayName: "Electric Gate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electric+Gate",
    },
    {
      lookupValue: "ElectricVehicleChargingStations",
      lookupDisplayName: "Electric Vehicle Charging Station(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245987",
    },
    {
      lookupValue: "Enclosed",
      lookupDisplayName: "Enclosed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Enclosed",
    },
    {
      lookupValue: "Garage",
      lookupDisplayName: "Garage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245989",
    },
    {
      lookupValue: "GarageDoorOpener",
      lookupDisplayName: "Garage Door Opener",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Garage+Door+Opener",
    },
    {
      lookupValue: "GarageFacesFront",
      lookupDisplayName: "Garage Faces Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Garage+Faces+Front",
    },
    {
      lookupValue: "GarageFacesRear",
      lookupDisplayName: "Garage Faces Rear",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Garage+Faces+Rear",
    },
    {
      lookupValue: "GarageFacesSide",
      lookupDisplayName: "Garage Faces Side",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Garage+Faces+Side",
    },
    {
      lookupValue: "Gated",
      lookupDisplayName: "Gated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245994",
    },
    {
      lookupValue: "GolfCartGarage",
      lookupDisplayName: "Golf Cart Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Golf+Cart+Garage",
    },
    {
      lookupValue: "Gravel",
      lookupDisplayName: "Gravel",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245996",
    },
    {
      lookupValue: "Guest",
      lookupDisplayName: "Guest",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Guest",
    },
    {
      lookupValue: "HeatedGarage",
      lookupDisplayName: "Heated Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Heated+Garage",
    },
    {
      lookupValue: "InsideEntrance",
      lookupDisplayName: "Inside Entrance",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Inside+Entrance",
    },
    {
      lookupValue: "KitchenLevel",
      lookupDisplayName: "Kitchen Level",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Kitchen+Level",
    },
    {
      lookupValue: "Leased",
      lookupDisplayName: "Leased",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Leased",
    },
    {
      lookupValue: "Lighted",
      lookupDisplayName: "Lighted",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lighted",
    },
    {
      lookupValue: "NoGarage",
      lookupDisplayName: "No Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+Garage",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246004",
    },
    {
      lookupValue: "OffSite",
      lookupDisplayName: "Off Site",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Off+Site",
    },
    {
      lookupValue: "OffStreet",
      lookupDisplayName: "Off Street",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Off+Street",
    },
    {
      lookupValue: "OnSite",
      lookupDisplayName: "On Site",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/On+Site",
    },
    {
      lookupValue: "OnStreet",
      lookupDisplayName: "On Street",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/On+Street",
    },
    {
      lookupValue: "Open",
      lookupDisplayName: "Open",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246009",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246010",
    },
    {
      lookupValue: "Outside",
      lookupDisplayName: "Outside",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246011",
    },
    {
      lookupValue: "Oversized",
      lookupDisplayName: "Oversized",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Oversized",
    },
    {
      lookupValue: "ParkingLot",
      lookupDisplayName: "Parking Lot",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Parking+Lot",
    },
    {
      lookupValue: "ParkingPad",
      lookupDisplayName: "Parking Pad",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Parking+Pad",
    },
    {
      lookupValue: "Paved",
      lookupDisplayName: "Paved",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246015",
    },
    {
      lookupValue: "PaverBlock",
      lookupDisplayName: "Paver Block",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Paver+Block",
    },
    {
      lookupValue: "PermitRequired",
      lookupDisplayName: "Permit Required",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Permit+Required",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246018",
    },
    {
      lookupValue: "RvAccessParking",
      lookupDisplayName: "RV Access/Parking",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246019",
    },
    {
      lookupValue: "RvCarport",
      lookupDisplayName: "RV Carport",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RV+Carport",
    },
    {
      lookupValue: "RvGarage",
      lookupDisplayName: "RV Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RV+Garage",
    },
    {
      lookupValue: "RvGated",
      lookupDisplayName: "RV Gated",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RV+Gated",
    },
    {
      lookupValue: "Secured",
      lookupDisplayName: "Secured",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Secured",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246024",
    },
    {
      lookupValue: "SharedDriveway",
      lookupDisplayName: "Shared Driveway",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shared+Driveway",
    },
    {
      lookupValue: "SideBySide",
      lookupDisplayName: "Side By Side",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Side+By+Side",
    },
    {
      lookupValue: "Storage",
      lookupDisplayName: "Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246027",
    },
    {
      lookupValue: "Tandem",
      lookupDisplayName: "Tandem",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tandem",
    },
    {
      lookupValue: "Unassigned",
      lookupDisplayName: "Unassigned",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unassigned",
    },
    {
      lookupValue: "Underground",
      lookupDisplayName: "Underground",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246030",
    },
    {
      lookupValue: "Unpaved",
      lookupDisplayName: "Unpaved",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unpaved",
    },
    {
      lookupValue: "Valet",
      lookupDisplayName: "Valet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Valet",
    },
    {
      lookupValue: "VariesByUnit",
      lookupDisplayName: "Varies by Unit",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246033",
    },
    {
      lookupValue: "WorkshopInGarage",
      lookupDisplayName: "Workshop in Garage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Workshop+in+Garage",
    },
  ],
  PatioAndPorchFeatures: [
    {
      lookupValue: "Awnings",
      lookupDisplayName: "Awning(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246038",
    },
    {
      lookupValue: "Covered",
      lookupDisplayName: "Covered",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246039",
    },
    {
      lookupValue: "Deck",
      lookupDisplayName: "Deck",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246040",
    },
    {
      lookupValue: "Enclosed",
      lookupDisplayName: "Enclosed",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246041",
    },
    {
      lookupValue: "FrontPorch",
      lookupDisplayName: "Front Porch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Front+Porch",
    },
    {
      lookupValue: "GlassEnclosed",
      lookupDisplayName: "Glass Enclosed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Glass+Enclosed",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246044",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246045",
    },
    {
      lookupValue: "Patio",
      lookupDisplayName: "Patio",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246046",
    },
    {
      lookupValue: "Porch",
      lookupDisplayName: "Porch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Porch",
    },
    {
      lookupValue: "RearPorch",
      lookupDisplayName: "Rear Porch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rear+Porch",
    },
    {
      lookupValue: "Screened",
      lookupDisplayName: "Screened",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Screened",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246050",
    },
    {
      lookupValue: "SidePorch",
      lookupDisplayName: "Side Porch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Side+Porch",
    },
    {
      lookupValue: "Terrace",
      lookupDisplayName: "Terrace",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Terrace",
    },
    {
      lookupValue: "WrapAround",
      lookupDisplayName: "Wrap Around",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wrap+Around",
    },
  ],
  Permission: [
    {
      lookupValue: "AgentOnly",
      lookupDisplayName: "Agent Only",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Agent+Only",
    },
    {
      lookupValue: "FirmOnly",
      lookupDisplayName: "Firm Only",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Firm+Only",
    },
    {
      lookupValue: "Idx",
      lookupDisplayName: "IDX",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IDX",
    },
    {
      lookupValue: "OfficeOnly",
      lookupDisplayName: "Office Only",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Office+Only",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245915",
    },
    {
      lookupValue: "Public",
      lookupDisplayName: "Public",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245916",
    },
    {
      lookupValue: "Vow",
      lookupDisplayName: "VOW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VOW",
    },
  ],
  PetsAllowed: [
    {
      lookupValue: "BreedRestrictions",
      lookupDisplayName: "Breed Restrictions",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Breed+Restrictions",
    },
    {
      lookupValue: "Call",
      lookupDisplayName: "Call",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Call",
    },
    {
      lookupValue: "CatsOk",
      lookupDisplayName: "Cats OK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cats+OK",
    },
    {
      lookupValue: "DogsOk",
      lookupDisplayName: "Dogs OK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dogs+OK",
    },
    {
      lookupValue: "No",
      lookupDisplayName: "No",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245842",
    },
    {
      lookupValue: "NumberLimit",
      lookupDisplayName: "Number Limit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Number+Limit",
    },
    {
      lookupValue: "SizeLimit",
      lookupDisplayName: "Size Limit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Size+Limit",
    },
    {
      lookupValue: "Yes",
      lookupDisplayName: "Yes",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245845",
    },
  ],
  PoolFeatures: [
    {
      lookupValue: "AboveGround",
      lookupDisplayName: "Above Ground",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Above+Ground",
    },
    {
      lookupValue: "Association",
      lookupDisplayName: "Association",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246056",
    },
    {
      lookupValue: "BlackBottom",
      lookupDisplayName: "Black Bottom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Black+Bottom",
    },
    {
      lookupValue: "Cabana",
      lookupDisplayName: "Cabana",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246058",
    },
    {
      lookupValue: "Community",
      lookupDisplayName: "Community",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246059",
    },
    {
      lookupValue: "DivingBoard",
      lookupDisplayName: "Diving Board",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Diving+Board",
    },
    {
      lookupValue: "ElectricHeat",
      lookupDisplayName: "Electric Heat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Electric+Heat",
    },
    {
      lookupValue: "EnergyStarQualifiedPoolPump",
      lookupDisplayName: "ENERGY STAR Qualified pool pump",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+pool+pump",
    },
    {
      lookupValue: "Fenced",
      lookupDisplayName: "Fenced",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246063",
    },
    {
      lookupValue: "Fiberglass",
      lookupDisplayName: "Fiberglass",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246064",
    },
    {
      lookupValue: "Filtered",
      lookupDisplayName: "Filtered",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Filtered",
    },
    {
      lookupValue: "GasHeat",
      lookupDisplayName: "Gas Heat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gas+Heat",
    },
    {
      lookupValue: "Gunite",
      lookupDisplayName: "Gunite",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gunite",
    },
    {
      lookupValue: "Heated",
      lookupDisplayName: "Heated",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Heated",
    },
    {
      lookupValue: "Indoor",
      lookupDisplayName: "Indoor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Indoor",
    },
    {
      lookupValue: "Infinity",
      lookupDisplayName: "Infinity",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Infinity",
    },
    {
      lookupValue: "InGround",
      lookupDisplayName: "In Ground",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Ground",
    },
    {
      lookupValue: "Lap",
      lookupDisplayName: "Lap",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lap",
    },
    {
      lookupValue: "Liner",
      lookupDisplayName: "Liner",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Liner",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246074",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246075",
    },
    {
      lookupValue: "OutdoorPool",
      lookupDisplayName: "Outdoor Pool",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Outdoor+Pool",
    },
    {
      lookupValue: "PoolCover",
      lookupDisplayName: "Pool Cover",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pool+Cover",
    },
    {
      lookupValue: "PoolSpaCombo",
      lookupDisplayName: "Pool/Spa Combo",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246077",
    },
    {
      lookupValue: "PoolSweep",
      lookupDisplayName: "Pool Sweep",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pool+Sweep",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246080",
    },
    {
      lookupValue: "SaltWater",
      lookupDisplayName: "Salt Water",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Salt+Water",
    },
    {
      lookupValue: "ScreenEnclosure",
      lookupDisplayName: "Screen Enclosure",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Screen+Enclosure",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246084",
    },
    {
      lookupValue: "SolarCover",
      lookupDisplayName: "Solar Cover",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Solar+Cover",
    },
    {
      lookupValue: "SolarHeat",
      lookupDisplayName: "Solar Heat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Solar+Heat",
    },
    {
      lookupValue: "Sport",
      lookupDisplayName: "Sport",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sport",
    },
    {
      lookupValue: "Tile",
      lookupDisplayName: "Tile",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246088",
    },
    {
      lookupValue: "Vinyl",
      lookupDisplayName: "Vinyl",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246089",
    },
    {
      lookupValue: "Waterfall",
      lookupDisplayName: "Waterfall",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246090",
    },
  ],
  Possession: [
    {
      lookupValue: "CloseOfEscrow",
      lookupDisplayName: "Close Of Escrow",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Close+Of+Escrow",
    },
    {
      lookupValue: "ClosePlus1Day",
      lookupDisplayName: "Close Plus 1 Day",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Close+Plus+1+Day",
    },
    {
      lookupValue: "ClosePlus2Days",
      lookupDisplayName: "Close Plus 2 Days",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Close+Plus+2+Days",
    },
    {
      lookupValue: "ClosePlus30Days",
      lookupDisplayName: "Close Plus 30 Days",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Close+Plus+30+Days",
    },
    {
      lookupValue: "ClosePlus3Days",
      lookupDisplayName: "Close Plus 3 Days",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Close+Plus+3+Days",
    },
    {
      lookupValue: "ClosePlus3To5Days",
      lookupDisplayName: "Close Plus 3 to 5 Days",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Close+Plus+3+to+5+Days",
    },
    {
      lookupValue: "Negotiable",
      lookupDisplayName: "Negotiable",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245853",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245858",
    },
    {
      lookupValue: "RentalAgreement",
      lookupDisplayName: "Rental Agreement",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rental+Agreement",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245855",
    },
    {
      lookupValue: "SellerRentBack",
      lookupDisplayName: "Seller Rent Back",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Seller+Rent+Back",
    },
    {
      lookupValue: "SubjectToTenantRights",
      lookupDisplayName: "Subject To Tenant Rights",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Subject+To+Tenant+Rights",
    },
  ],
  PossibleUse: [
    {
      lookupValue: "Agricultural",
      lookupDisplayName: "Agricultural",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245919",
    },
    {
      lookupValue: "Cattle",
      lookupDisplayName: "Cattle",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245920",
    },
    {
      lookupValue: "Commercial",
      lookupDisplayName: "Commercial",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245921",
    },
    {
      lookupValue: "Dairy",
      lookupDisplayName: "Dairy",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245922",
    },
    {
      lookupValue: "Development",
      lookupDisplayName: "Development",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Development",
    },
    {
      lookupValue: "Farm",
      lookupDisplayName: "Farm",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245924",
    },
    {
      lookupValue: "Fishery",
      lookupDisplayName: "Fishery",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245925",
    },
    {
      lookupValue: "Grazing",
      lookupDisplayName: "Grazing",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245926",
    },
    {
      lookupValue: "HighwayTouristService",
      lookupDisplayName: "Highway/Tourist Service",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245927",
    },
    {
      lookupValue: "Horses",
      lookupDisplayName: "Horses",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245928",
    },
    {
      lookupValue: "Hunting",
      lookupDisplayName: "Hunting",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245929",
    },
    {
      lookupValue: "Industrial",
      lookupDisplayName: "Industrial",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245930",
    },
    {
      lookupValue: "Investment",
      lookupDisplayName: "Investment",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245931",
    },
    {
      lookupValue: "Livestock",
      lookupDisplayName: "Livestock",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245932",
    },
    {
      lookupValue: "ManufacturedHome",
      lookupDisplayName: "Manufactured Home",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245933",
    },
    {
      lookupValue: "MiniStorage",
      lookupDisplayName: "Mini-Storage",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245934",
    },
    {
      lookupValue: "MultiFamily",
      lookupDisplayName: "Multi-Family",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245935",
    },
    {
      lookupValue: "Orchard",
      lookupDisplayName: "Orchard",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245936",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245937",
    },
    {
      lookupValue: "Pasture",
      lookupDisplayName: "Pasture",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245938",
    },
    {
      lookupValue: "PlaceOfWorship",
      lookupDisplayName: "Place of Worship",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245939",
    },
    {
      lookupValue: "Poultry",
      lookupDisplayName: "Poultry",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245940",
    },
    {
      lookupValue: "Ranch",
      lookupDisplayName: "Ranch",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245941",
    },
    {
      lookupValue: "Recreational",
      lookupDisplayName: "Recreational",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245942",
    },
    {
      lookupValue: "Residential",
      lookupDisplayName: "Residential",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245943",
    },
    {
      lookupValue: "Retail",
      lookupDisplayName: "Retail",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245944",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245945",
    },
    {
      lookupValue: "SingleFamily",
      lookupDisplayName: "Single Family",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245946",
    },
    {
      lookupValue: "Subdevelopment",
      lookupDisplayName: "Subdevelopment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Subdevelopment",
    },
    {
      lookupValue: "Timber",
      lookupDisplayName: "Timber",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245948",
    },
    {
      lookupValue: "Unimproved",
      lookupDisplayName: "Unimproved",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245949",
    },
    {
      lookupValue: "Vacant",
      lookupDisplayName: "Vacant",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245950",
    },
    {
      lookupValue: "Warehouse",
      lookupDisplayName: "Warehouse",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245951",
    },
  ],
  PowerProductionAnnualStatus: [
    {
      lookupValue: "Actual",
      lookupDisplayName: "Actual",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Actual",
    },
    {
      lookupValue: "Estimated",
      lookupDisplayName: "Estimated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245861",
    },
    {
      lookupValue: "PartiallyEstimated",
      lookupDisplayName: "Partially Estimated",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Partially+Estimated",
    },
  ],
  PowerProductionType: [
    {
      lookupValue: "Photovoltaics",
      lookupDisplayName: "Photovoltaics",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Photovoltaics",
    },
    {
      lookupValue: "Wind",
      lookupDisplayName: "Wind",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245865",
    },
  ],
  PreferredAddress: [
    {
      lookupValue: "Home",
      lookupDisplayName: "Home",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245953",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245954",
    },
    {
      lookupValue: "Work",
      lookupDisplayName: "Work",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Work",
    },
  ],
  PreferredPhone: [
    {
      lookupValue: "Direct",
      lookupDisplayName: "Direct",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245957",
    },
    {
      lookupValue: "Home",
      lookupDisplayName: "Home",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245958",
    },
    {
      lookupValue: "Mobile",
      lookupDisplayName: "Mobile",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245959",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245960",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245961",
    },
    {
      lookupValue: "TollFree",
      lookupDisplayName: "Toll Free",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245962",
    },
    {
      lookupValue: "Voicemail",
      lookupDisplayName: "Voicemail",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245963",
    },
  ],
  PropertyCondition: [
    {
      lookupValue: "Fixer",
      lookupDisplayName: "Fixer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fixer",
    },
    {
      lookupValue: "NewConstruction",
      lookupDisplayName: "New Construction",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/New+Construction",
    },
    {
      lookupValue: "UnderConstruction",
      lookupDisplayName: "Under Construction",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Under+Construction",
    },
    {
      lookupValue: "UpdatedRemodeled",
      lookupDisplayName: "Updated/Remodeled",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245870",
    },
  ],
  PropertySubType: [
    {
      lookupValue: "Agriculture",
      lookupDisplayName: "Agriculture",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245890",
    },
    {
      lookupValue: "Apartment",
      lookupDisplayName: "Apartment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Apartment",
    },
    {
      lookupValue: "BoatSlip",
      lookupDisplayName: "Boat Slip",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Boat+Slip",
    },
    {
      lookupValue: "Business",
      lookupDisplayName: "Business",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245891",
    },
    {
      lookupValue: "Cabin",
      lookupDisplayName: "Cabin",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cabin",
    },
    {
      lookupValue: "Condominium",
      lookupDisplayName: "Condominium",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Condominium",
    },
    {
      lookupValue: "DeededParking",
      lookupDisplayName: "Deeded Parking",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Deeded+Parking",
    },
    {
      lookupValue: "Duplex",
      lookupDisplayName: "Duplex",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Duplex",
    },
    {
      lookupValue: "Farm",
      lookupDisplayName: "Farm",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245878",
    },
    {
      lookupValue: "HotelMotel",
      lookupDisplayName: "Hotel/Motel",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hotel-Motel",
    },
    {
      lookupValue: "Industrial",
      lookupDisplayName: "Industrial",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245893",
    },
    {
      lookupValue: "ManufacturedHome",
      lookupDisplayName: "Manufactured Home",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manufactured+Home",
    },
    {
      lookupValue: "ManufacturedOnLand",
      lookupDisplayName: "Manufactured On Land",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manufactured+On+Land",
    },
    {
      lookupValue: "MixedUse",
      lookupDisplayName: "Mixed Use",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mixed+Use",
    },
    {
      lookupValue: "MobileHome",
      lookupDisplayName: "Mobile Home",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mobile+Home",
    },
    {
      lookupValue: "MultiFamily",
      lookupDisplayName: "Multi Family",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Multi+Family",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245896",
    },
    {
      lookupValue: "OwnYourOwn",
      lookupDisplayName: "Own Your Own",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Own+Your+Own",
    },
    {
      lookupValue: "Quadruplex",
      lookupDisplayName: "Quadruplex",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Quadruplex",
    },
    {
      lookupValue: "Ranch",
      lookupDisplayName: "Ranch",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ranch",
    },
    {
      lookupValue: "Retail",
      lookupDisplayName: "Retail",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245897",
    },
    {
      lookupValue: "SingleFamilyResidence",
      lookupDisplayName: "Single Family Residence",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Single+Family+Residence",
    },
    {
      lookupValue: "StockCooperative",
      lookupDisplayName: "Stock Cooperative",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stock+Cooperative",
    },
    {
      lookupValue: "Timeshare",
      lookupDisplayName: "Timeshare",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Timeshare",
    },
    {
      lookupValue: "Townhouse",
      lookupDisplayName: "Townhouse",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Townhouse",
    },
    {
      lookupValue: "Triplex",
      lookupDisplayName: "Triplex",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Triplex",
    },
    {
      lookupValue: "UnimprovedLand",
      lookupDisplayName: "Unimproved Land",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Unimproved+Land",
    },
    {
      lookupValue: "Warehouse",
      lookupDisplayName: "Warehouse",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245899",
    },
  ],
  PropertyType: [
    {
      lookupValue: "BusinessOpportunity",
      lookupDisplayName: "Business Opportunity",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245901",
    },
    {
      lookupValue: "CommercialLease",
      lookupDisplayName: "Commercial Lease",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245902",
    },
    {
      lookupValue: "CommercialSale",
      lookupDisplayName: "Commercial Sale",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245903",
    },
    {
      lookupValue: "Farm",
      lookupDisplayName: "Farm",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245904",
    },
    {
      lookupValue: "Land",
      lookupDisplayName: "Land",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245905",
    },
    {
      lookupValue: "ManufacturedInPark",
      lookupDisplayName: "Manufactured In Park",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245906",
    },
    {
      lookupValue: "Residential",
      lookupDisplayName: "Residential",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245907",
    },
    {
      lookupValue: "ResidentialIncome",
      lookupDisplayName: "Residential Income",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245908",
    },
    {
      lookupValue: "ResidentialLease",
      lookupDisplayName: "Residential Lease",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29245909",
    },
  ],
  QueueTransactionType: [
    {
      lookupValue: "Add",
      lookupDisplayName: "Add",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Add",
    },
    {
      lookupValue: "Change",
      lookupDisplayName: "Change",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Change",
    },
    {
      lookupValue: "Delete",
      lookupDisplayName: "Delete",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246733",
    },
  ],
  ReasonActiveOrDisabled: [
    {
      lookupValue: "AgentDisabled",
      lookupDisplayName: "Agent Disabled",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Agent+Disabled",
    },
    {
      lookupValue: "ClientDisabled",
      lookupDisplayName: "Client Disabled",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Client+Disabled",
    },
    {
      lookupValue: "ConciergeNotification",
      lookupDisplayName: "Concierge Notification",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Concierge+Notification",
    },
    {
      lookupValue: "FinalIgnoredWarning",
      lookupDisplayName: "Final Ignored Warning",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Final+Ignored+Warning",
    },
    {
      lookupValue: "Ignored",
      lookupDisplayName: "Ignored",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ignored",
    },
    {
      lookupValue: "InitialIgnoredWarning",
      lookupDisplayName: "Initial Ignored Warning",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Initial+Ignored+Warning",
    },
    {
      lookupValue: "Invalid",
      lookupDisplayName: "Invalid",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Invalid",
    },
    {
      lookupValue: "NoListingsFound",
      lookupDisplayName: "No Listings Found",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+Listings+Found",
    },
    {
      lookupValue: "NoListingsFoundWarning",
      lookupDisplayName: "No Listings Found Warning",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/No+Listings+Found+Warning",
    },
    {
      lookupValue: "NoOneToSendTo",
      lookupDisplayName: "No One To Send To",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+One+To+Send+To",
    },
    {
      lookupValue: "OverLimit",
      lookupDisplayName: "Over Limit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Over+Limit",
    },
    {
      lookupValue: "ReActivated",
      lookupDisplayName: "Re-Activated",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Re-Activated",
    },
    {
      lookupValue: "Revised",
      lookupDisplayName: "Revised",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Revised",
    },
    {
      lookupValue: "SearchFailing",
      lookupDisplayName: "Search Failing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Search+Failing",
    },
    {
      lookupValue: "WelcomeEmailIgnored",
      lookupDisplayName: "Welcome Email Ignored",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Welcome+Email+Ignored",
    },
    {
      lookupValue: "WelcomeEmailIgnoredWarning",
      lookupDisplayName: "Welcome Email Ignored Warning",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Welcome+Email+Ignored+Warning",
    },
  ],
  RentIncludes: [
    {
      lookupValue: "AllUtilities",
      lookupDisplayName: "All Utilities",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246093",
    },
    {
      lookupValue: "CableTv",
      lookupDisplayName: "Cable TV",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246094",
    },
    {
      lookupValue: "Electricity",
      lookupDisplayName: "Electricity",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246095",
    },
    {
      lookupValue: "Gardener",
      lookupDisplayName: "Gardener",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246096",
    },
    {
      lookupValue: "Gas",
      lookupDisplayName: "Gas",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246097",
    },
    {
      lookupValue: "Internet",
      lookupDisplayName: "Internet",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Internet",
    },
    {
      lookupValue: "Management",
      lookupDisplayName: "Management",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246099",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246100",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246105",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246101",
    },
    {
      lookupValue: "Sewer",
      lookupDisplayName: "Sewer",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246102",
    },
    {
      lookupValue: "TrashCollection",
      lookupDisplayName: "Trash Collection",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246103",
    },
    {
      lookupValue: "Water",
      lookupDisplayName: "Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246104",
    },
  ],
  ResourceName: [
    {
      lookupValue: "Contacts",
      lookupDisplayName: "Contacts",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246107",
    },
    {
      lookupValue: "Member",
      lookupDisplayName: "Member",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246108",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246109",
    },
    {
      lookupValue: "Property",
      lookupDisplayName: "Property",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Property",
    },
  ],
  RoadFrontageType: [
    {
      lookupValue: "Alley",
      lookupDisplayName: "Alley",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Alley",
    },
    {
      lookupValue: "CityStreet",
      lookupDisplayName: "City Street",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/City+Street",
    },
    {
      lookupValue: "CountyRoad",
      lookupDisplayName: "County Road",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/County+Road",
    },
    {
      lookupValue: "Easement",
      lookupDisplayName: "Easement",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Easement",
    },
    {
      lookupValue: "Freeway",
      lookupDisplayName: "Freeway",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Freeway",
    },
    {
      lookupValue: "Highway",
      lookupDisplayName: "Highway",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Highway",
    },
    {
      lookupValue: "Interstate",
      lookupDisplayName: "Interstate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Interstate",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246205",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246206",
    },
    {
      lookupValue: "PrivateRoad",
      lookupDisplayName: "Private Road",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Private+Road",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246208",
    },
    {
      lookupValue: "StateRoad",
      lookupDisplayName: "State Road",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/State+Road",
    },
    {
      lookupValue: "Unimproved",
      lookupDisplayName: "Unimproved",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246210",
    },
  ],
  RoadResponsibility: [
    {
      lookupValue: "PrivateMaintainedRoad",
      lookupDisplayName: "Private Maintained Road",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Private+Maintained+Road",
    },
    {
      lookupValue: "PublicMaintainedRoad",
      lookupDisplayName: "Public Maintained Road",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Public+Maintained+Road",
    },
    {
      lookupValue: "RoadMaintenanceAgreement",
      lookupDisplayName: "Road Maintenance Agreement",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Road+Maintenance+Agreement",
    },
  ],
  RoadSurfaceType: [
    {
      lookupValue: "AlleyPaved",
      lookupDisplayName: "Alley Paved",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Alley+Paved",
    },
    {
      lookupValue: "Asphalt",
      lookupDisplayName: "Asphalt",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246217",
    },
    {
      lookupValue: "ChipAndSeal",
      lookupDisplayName: "Chip And Seal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Chip+And+Seal",
    },
    {
      lookupValue: "Concrete",
      lookupDisplayName: "Concrete",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246219",
    },
    {
      lookupValue: "Dirt",
      lookupDisplayName: "Dirt",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Dirt",
    },
    {
      lookupValue: "Gravel",
      lookupDisplayName: "Gravel",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gravel",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246222",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246223",
    },
    {
      lookupValue: "Paved",
      lookupDisplayName: "Paved",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Paved",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246225",
    },
    {
      lookupValue: "Unimproved",
      lookupDisplayName: "Unimproved",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246226",
    },
  ],
  Roof: [
    {
      lookupValue: "Aluminum",
      lookupDisplayName: "Aluminum",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aluminum",
    },
    {
      lookupValue: "AsbestosShingle",
      lookupDisplayName: "Asbestos Shingle",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Asbestos+Shingle",
    },
    {
      lookupValue: "Asphalt",
      lookupDisplayName: "Asphalt",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246114",
    },
    {
      lookupValue: "Bahama",
      lookupDisplayName: "Bahama",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bahama",
    },
    {
      lookupValue: "Barrel",
      lookupDisplayName: "Barrel",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Barrel",
    },
    {
      lookupValue: "Bituthene",
      lookupDisplayName: "Bituthene",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bituthene",
    },
    {
      lookupValue: "BuiltUp",
      lookupDisplayName: "Built-Up",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Built-Up",
    },
    {
      lookupValue: "Composition",
      lookupDisplayName: "Composition",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Composition",
    },
    {
      lookupValue: "Concrete",
      lookupDisplayName: "Concrete",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246120",
    },
    {
      lookupValue: "Copper",
      lookupDisplayName: "Copper",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Copper",
    },
    {
      lookupValue: "Elastomeric",
      lookupDisplayName: "Elastomeric",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Elastomeric",
    },
    {
      lookupValue: "Fiberglass",
      lookupDisplayName: "Fiberglass",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fiberglass",
    },
    {
      lookupValue: "Flat",
      lookupDisplayName: "Flat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Flat",
    },
    {
      lookupValue: "FlatTile",
      lookupDisplayName: "Flat Tile",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Flat+Tile",
    },
    {
      lookupValue: "Foam",
      lookupDisplayName: "Foam",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Foam",
    },
    {
      lookupValue: "GreenRoof",
      lookupDisplayName: "Green Roof",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Green+Roof",
    },
    {
      lookupValue: "Mansard",
      lookupDisplayName: "Mansard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mansard",
    },
    {
      lookupValue: "Membrane",
      lookupDisplayName: "Membrane",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Membrane",
    },
    {
      lookupValue: "Metal",
      lookupDisplayName: "Metal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Metal",
    },
    {
      lookupValue: "Mixed",
      lookupDisplayName: "Mixed",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246131",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246132",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246133",
    },
    {
      lookupValue: "RolledHotMop",
      lookupDisplayName: "Rolled/Hot Mop",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246134",
    },
    {
      lookupValue: "Rubber",
      lookupDisplayName: "Rubber",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rubber",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246136",
    },
    {
      lookupValue: "Shake",
      lookupDisplayName: "Shake",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shake",
    },
    {
      lookupValue: "Shingle",
      lookupDisplayName: "Shingle",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shingle",
    },
    {
      lookupValue: "Slate",
      lookupDisplayName: "Slate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Slate",
    },
    {
      lookupValue: "SpanishTile",
      lookupDisplayName: "Spanish Tile",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Spanish+Tile",
    },
    {
      lookupValue: "Stone",
      lookupDisplayName: "Stone",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246141",
    },
    {
      lookupValue: "Synthetic",
      lookupDisplayName: "Synthetic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Synthetic",
    },
    {
      lookupValue: "TarGravel",
      lookupDisplayName: "Tar/Gravel",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246143",
    },
    {
      lookupValue: "Tile",
      lookupDisplayName: "Tile",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tile",
    },
    {
      lookupValue: "Wood",
      lookupDisplayName: "Wood",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wood",
    },
  ],
  "Room[type]LengthWidthSource": [
    {
      lookupValue: "Appraiser",
      lookupDisplayName: "Appraiser",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246228",
    },
    {
      lookupValue: "Assessor",
      lookupDisplayName: "Assessor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246229",
    },
    {
      lookupValue: "Builder",
      lookupDisplayName: "Builder",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246230",
    },
    {
      lookupValue: "Estimated",
      lookupDisplayName: "Estimated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246231",
    },
    {
      lookupValue: "GisCalculated",
      lookupDisplayName: "GIS Calculated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246232",
    },
    {
      lookupValue: "Measured",
      lookupDisplayName: "Measured",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246233",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246234",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246235",
    },
    {
      lookupValue: "PublicRecords",
      lookupDisplayName: "Public Records",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246236",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246237",
    },
    {
      lookupValue: "Survey",
      lookupDisplayName: "Survey",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246238",
    },
  ],
  "Room[type]Level": [
    {
      lookupValue: "Basement",
      lookupDisplayName: "Basement",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246240",
    },
    {
      lookupValue: "First",
      lookupDisplayName: "First",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246241",
    },
    {
      lookupValue: "Lower",
      lookupDisplayName: "Lower",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lower",
    },
    {
      lookupValue: "Main",
      lookupDisplayName: "Main",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246243",
    },
    {
      lookupValue: "Second",
      lookupDisplayName: "Second",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246244",
    },
    {
      lookupValue: "Third",
      lookupDisplayName: "Third",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246245",
    },
    {
      lookupValue: "Upper",
      lookupDisplayName: "Upper",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Upper",
    },
  ],
  RoomType: [
    {
      lookupValue: "Basement",
      lookupDisplayName: "Basement",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246147",
    },
    {
      lookupValue: "Bathroom",
      lookupDisplayName: "Bathroom",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246179",
    },
    {
      lookupValue: "Bathroom1",
      lookupDisplayName: "Bathroom 1",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bathroom+1",
    },
    {
      lookupValue: "Bathroom2",
      lookupDisplayName: "Bathroom 2",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bathroom+2",
    },
    {
      lookupValue: "Bathroom3",
      lookupDisplayName: "Bathroom 3",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bathroom+3",
    },
    {
      lookupValue: "Bathroom4",
      lookupDisplayName: "Bathroom 4",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bathroom+4",
    },
    {
      lookupValue: "Bathroom5",
      lookupDisplayName: "Bathroom 5",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bathroom+5",
    },
    {
      lookupValue: "Bedroom",
      lookupDisplayName: "Bedroom",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246178",
    },
    {
      lookupValue: "Bedroom1",
      lookupDisplayName: "Bedroom 1",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bedroom+1",
    },
    {
      lookupValue: "Bedroom2",
      lookupDisplayName: "Bedroom 2",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bedroom+2",
    },
    {
      lookupValue: "Bedroom3",
      lookupDisplayName: "Bedroom 3",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bedroom+3",
    },
    {
      lookupValue: "Bedroom4",
      lookupDisplayName: "Bedroom 4",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bedroom+4",
    },
    {
      lookupValue: "Bedroom5",
      lookupDisplayName: "Bedroom 5",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bedroom+5",
    },
    {
      lookupValue: "BonusRoom",
      lookupDisplayName: "Bonus Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246158",
    },
    {
      lookupValue: "Den",
      lookupDisplayName: "Den",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246159",
    },
    {
      lookupValue: "DiningRoom",
      lookupDisplayName: "Dining Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246160",
    },
    {
      lookupValue: "ExerciseRoom",
      lookupDisplayName: "Exercise Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246161",
    },
    {
      lookupValue: "FamilyRoom",
      lookupDisplayName: "Family Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246162",
    },
    {
      lookupValue: "GameRoom",
      lookupDisplayName: "Game Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246163",
    },
    {
      lookupValue: "GreatRoom",
      lookupDisplayName: "Great Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246164",
    },
    {
      lookupValue: "Gym",
      lookupDisplayName: "Gym",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246165",
    },
    {
      lookupValue: "Kitchen",
      lookupDisplayName: "Kitchen",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246166",
    },
    {
      lookupValue: "Laundry",
      lookupDisplayName: "Laundry",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246167",
    },
    {
      lookupValue: "Library",
      lookupDisplayName: "Library",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246168",
    },
    {
      lookupValue: "LivingRoom",
      lookupDisplayName: "Living Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246169",
    },
    {
      lookupValue: "Loft",
      lookupDisplayName: "Loft",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246170",
    },
    {
      lookupValue: "MasterBathroom",
      lookupDisplayName: "Master Bathroom",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246171",
    },
    {
      lookupValue: "MasterBedroom",
      lookupDisplayName: "Master Bedroom",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246172",
    },
    {
      lookupValue: "MediaRoom",
      lookupDisplayName: "Media Room",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246173",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246174",
    },
    {
      lookupValue: "Sauna",
      lookupDisplayName: "Sauna",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246175",
    },
    {
      lookupValue: "UtilityRoom",
      lookupDisplayName: "Utility Room",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Utility+Room",
    },
    {
      lookupValue: "Workshop",
      lookupDisplayName: "Workshop",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246177",
    },
  ],
  RuleFormat: [
    {
      lookupValue: "Javascript",
      lookupDisplayName: "JavaScript",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/JavaScript",
    },
    {
      lookupValue: "OdataFilter",
      lookupDisplayName: "$filter",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246248",
    },
    {
      lookupValue: "REBR",
      lookupDisplayName: "REBR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/REBR",
    },
    {
      lookupValue: "RetsValidation",
      lookupDisplayName: "RetsValidation",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RetsValidation",
    },
  ],
  ScheduleType: [
    {
      lookupValue: "ASAP",
      lookupDisplayName: "ASAP",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ASAP",
    },
    {
      lookupValue: "Daily",
      lookupDisplayName: "Daily",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246434",
    },
    {
      lookupValue: "Monthly",
      lookupDisplayName: "Monthly",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246435",
    },
  ],
  SearchQueryType: [
    {
      lookupValue: "DMQL2",
      lookupDisplayName: "DMQL2",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DMQL2",
    },
    {
      lookupValue: "OdataFilter",
      lookupDisplayName: "$filter",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/%24filter",
    },
  ],
  SecurityFeatures: [
    {
      lookupValue: "BuildingSecurity",
      lookupDisplayName: "Building Security",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Building+Security",
    },
    {
      lookupValue: "CarbonMonoxideDetectors",
      lookupDisplayName: "Carbon Monoxide Detector(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246457",
    },
    {
      lookupValue: "ClosedCircuitCameras",
      lookupDisplayName: "Closed Circuit Camera(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246458",
    },
    {
      lookupValue: "FireAlarm",
      lookupDisplayName: "Fire Alarm",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fire+Alarm",
    },
    {
      lookupValue: "FireEscape",
      lookupDisplayName: "Fire Escape",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Fire+Escape",
    },
    {
      lookupValue: "FireSprinklerSystem",
      lookupDisplayName: "Fire Sprinkler System",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Fire+Sprinkler+System",
    },
    {
      lookupValue: "Firewalls",
      lookupDisplayName: "Firewall(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246462",
    },
    {
      lookupValue: "GatedCommunity",
      lookupDisplayName: "Gated Community",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gated+Community",
    },
    {
      lookupValue: "GatedWithGuard",
      lookupDisplayName: "Gated with Guard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gated+with+Guard",
    },
    {
      lookupValue: "KeyCardEntry",
      lookupDisplayName: "Key Card Entry",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Key+Card+Entry",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246466",
    },
    {
      lookupValue: "PanicAlarm",
      lookupDisplayName: "Panic Alarm",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Panic+Alarm",
    },
    {
      lookupValue: "Prewired",
      lookupDisplayName: "Prewired",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Prewired",
    },
    {
      lookupValue: "SecuredGarageParking",
      lookupDisplayName: "Secured Garage/Parking",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246469",
    },
    {
      lookupValue: "SecurityFence",
      lookupDisplayName: "Security Fence",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Security+Fence",
    },
    {
      lookupValue: "SecurityGate",
      lookupDisplayName: "Security Gate",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Security+Gate",
    },
    {
      lookupValue: "SecurityGuard",
      lookupDisplayName: "Security Guard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Security+Guard",
    },
    {
      lookupValue: "SecurityLights",
      lookupDisplayName: "Security Lights",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Security+Lights",
    },
    {
      lookupValue: "SecurityService",
      lookupDisplayName: "Security Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Security+Service",
    },
    {
      lookupValue: "SecuritySystem",
      lookupDisplayName: "Security System",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Security+System",
    },
    {
      lookupValue: "SecuritySystemLeased",
      lookupDisplayName: "Security System Leased",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Security+System+Leased",
    },
    {
      lookupValue: "SecuritySystemOwned",
      lookupDisplayName: "Security System Owned",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Security+System+Owned",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246478",
    },
    {
      lookupValue: "SmokeDetectors",
      lookupDisplayName: "Smoke Detector(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246479",
    },
    {
      lookupValue: "TwentyFourHourSecurity",
      lookupDisplayName: "24 Hour Security",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/24+Hour+Security",
    },
    {
      lookupValue: "VariesByUnit",
      lookupDisplayName: "Varies By Unit",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246480",
    },
    {
      lookupValue: "WindowBars",
      lookupDisplayName: "Window Bars",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Window+Bars",
    },
    {
      lookupValue: "WindowBarsWithQuickRelease",
      lookupDisplayName: "Window Bars with Quick Release",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Window+Bars+with+Quick+Release",
    },
  ],
  Sewer: [
    {
      lookupValue: "AerobicSeptic",
      lookupDisplayName: "Aerobic Septic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aerobic+Septic",
    },
    {
      lookupValue: "Cesspool",
      lookupDisplayName: "Cesspool",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cesspool",
    },
    {
      lookupValue: "EngineeredSeptic",
      lookupDisplayName: "Engineered Septic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Engineered+Septic",
    },
    {
      lookupValue: "HoldingTank",
      lookupDisplayName: "Holding Tank",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Holding+Tank",
    },
    {
      lookupValue: "MoundSeptic",
      lookupDisplayName: "Mound Septic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Mound+Septic",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246259",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246260",
    },
    {
      lookupValue: "PercTestOnFile",
      lookupDisplayName: "Perc Test On File",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Perc+Test+On+File",
    },
    {
      lookupValue: "PercTestRequired",
      lookupDisplayName: "Perc Test Required",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Perc+Test+Required",
    },
    {
      lookupValue: "PrivateSewer",
      lookupDisplayName: "Private Sewer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Private+Sewer",
    },
    {
      lookupValue: "PublicSewer",
      lookupDisplayName: "Public Sewer",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Public+Sewer",
    },
    {
      lookupValue: "SepticNeeded",
      lookupDisplayName: "Septic Needed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Septic+Needed",
    },
    {
      lookupValue: "SepticTank",
      lookupDisplayName: "Septic Tank",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Septic+Tank",
    },
    {
      lookupValue: "SharedSeptic",
      lookupDisplayName: "Shared Septic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shared+Septic",
    },
    {
      lookupValue: "Unknown",
      lookupDisplayName: "Unknown",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246268",
    },
  ],
  ShowingContactType: [
    {
      lookupValue: "Agent",
      lookupDisplayName: "Agent",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246270",
    },
    {
      lookupValue: "Occupant",
      lookupDisplayName: "Occupant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Occupant",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246272",
    },
    {
      lookupValue: "PropertyManager",
      lookupDisplayName: "Property Manager",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246273",
    },
  ],
  ShowingRequirements: [
    {
      lookupValue: "AppointmentOnly",
      lookupDisplayName: "Appointment Only",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Appointment+Only",
    },
    {
      lookupValue: "CallListingAgent",
      lookupDisplayName: "Call Listing Agent",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246486",
    },
    {
      lookupValue: "CallListingOffice",
      lookupDisplayName: "Call Listing Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246487",
    },
    {
      lookupValue: "CallManager",
      lookupDisplayName: "Call Manager",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Call+Manager",
    },
    {
      lookupValue: "CallOwner",
      lookupDisplayName: "Call Owner",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Call+Owner",
    },
    {
      lookupValue: "CallTenant",
      lookupDisplayName: "Call Tenant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Call+Tenant",
    },
    {
      lookupValue: "CombinationLockBox",
      lookupDisplayName: "Combination Lock Box",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Combination+Lock+Box",
    },
    {
      lookupValue: "DaySleeper",
      lookupDisplayName: "Day Sleeper",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Day+Sleeper",
    },
    {
      lookupValue: "DoNotShow",
      lookupDisplayName: "Do Not Show",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Do+Not+Show",
    },
    {
      lookupValue: "EmailListingAgent",
      lookupDisplayName: "Email Listing Agent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Email+Listing+Agent",
    },
    {
      lookupValue: "KeyInOffice",
      lookupDisplayName: "Key In Office",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Key+In+Office",
    },
    {
      lookupValue: "Lockbox",
      lookupDisplayName: "Lockbox",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lockbox",
    },
    {
      lookupValue: "NoLockbox",
      lookupDisplayName: "No Lockbox",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+Lockbox",
    },
    {
      lookupValue: "NoSign",
      lookupDisplayName: "No Sign",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/No+Sign",
    },
    {
      lookupValue: "Occupied",
      lookupDisplayName: "Occupied",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Occupied",
    },
    {
      lookupValue: "PetsOnPremises",
      lookupDisplayName: "Pet(s) on Premises",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Pet%28s%29+on+Premises",
    },
    {
      lookupValue: "RestrictedHours",
      lookupDisplayName: "Restricted Hours",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Restricted+Hours",
    },
    {
      lookupValue: "SecuritySystem",
      lookupDisplayName: "Security System",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246502",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246503",
    },
    {
      lookupValue: "ShowingService",
      lookupDisplayName: "Showing Service",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Showing+Service",
    },
    {
      lookupValue: "TextListingAgent",
      lookupDisplayName: "Text Listing Agent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Text+Listing+Agent",
    },
    {
      lookupValue: "ToBeBuilt",
      lookupDisplayName: "To Be Built",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/To+Be+Built",
    },
    {
      lookupValue: "TwentyFourHourNotice",
      lookupDisplayName: "24 Hour Notice",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/24+Hour+Notice",
    },
    {
      lookupValue: "UnderConstruction",
      lookupDisplayName: "Under Construction",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246506",
    },
  ],
  Skirt: [
    {
      lookupValue: "Aluminum",
      lookupDisplayName: "Aluminum",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246275",
    },
    {
      lookupValue: "Block",
      lookupDisplayName: "Block",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246276",
    },
    {
      lookupValue: "Brick",
      lookupDisplayName: "Brick",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246277",
    },
    {
      lookupValue: "Combination",
      lookupDisplayName: "Combination",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246278",
    },
    {
      lookupValue: "Concrete",
      lookupDisplayName: "Concrete",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246279",
    },
    {
      lookupValue: "Fiberglass",
      lookupDisplayName: "Fiberglass",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246280",
    },
    {
      lookupValue: "Frame",
      lookupDisplayName: "Frame",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246281",
    },
    {
      lookupValue: "Glass",
      lookupDisplayName: "Glass",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246282",
    },
    {
      lookupValue: "Masonite",
      lookupDisplayName: "Masonite",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246283",
    },
    {
      lookupValue: "Metal",
      lookupDisplayName: "Metal",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246284",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246285",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246286",
    },
    {
      lookupValue: "Steel",
      lookupDisplayName: "Steel",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Steel",
    },
    {
      lookupValue: "Stone",
      lookupDisplayName: "Stone",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246288",
    },
    {
      lookupValue: "Stucco",
      lookupDisplayName: "Stucco",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246289",
    },
    {
      lookupValue: "Synthetic",
      lookupDisplayName: "Synthetic",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246290",
    },
    {
      lookupValue: "Unknown",
      lookupDisplayName: "Unknown",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246291",
    },
    {
      lookupValue: "Vinyl",
      lookupDisplayName: "Vinyl",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Vinyl",
    },
    {
      lookupValue: "Wood",
      lookupDisplayName: "Wood",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246293",
    },
  ],
  SocialMediaType: [
    {
      lookupValue: "Blog",
      lookupDisplayName: "Blog",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Blog",
    },
    {
      lookupValue: "Digg",
      lookupDisplayName: "Digg",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Digg",
    },
    {
      lookupValue: "Facebook",
      lookupDisplayName: "Facebook",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Facebook",
    },
    {
      lookupValue: "FacebookMessenger",
      lookupDisplayName: "Facebook Messenger",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Facebook+Messenger",
    },
    {
      lookupValue: "Googleplus",
      lookupDisplayName: "GooglePlus",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/GooglePlus",
    },
    {
      lookupValue: "iMessage",
      lookupDisplayName: "iMessage",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/iMessage",
    },
    {
      lookupValue: "Instagram",
      lookupDisplayName: "Instagram",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Instagram",
    },
    {
      lookupValue: "Linkedin",
      lookupDisplayName: "LinkedIn",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/LinkedIn",
    },
    {
      lookupValue: "Pinterest",
      lookupDisplayName: "Pinterest",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Pinterest",
    },
    {
      lookupValue: "Reddit",
      lookupDisplayName: "Reddit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Reddit",
    },
    {
      lookupValue: "Slack",
      lookupDisplayName: "Slack",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Slack",
    },
    {
      lookupValue: "Snapchat",
      lookupDisplayName: "Snapchat",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Snapchat",
    },
    {
      lookupValue: "Stumbleupon",
      lookupDisplayName: "StumbleUpon",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/StumbleUpon",
    },
    {
      lookupValue: "Tumblr",
      lookupDisplayName: "Tumblr",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tumblr",
    },
    {
      lookupValue: "Twitter",
      lookupDisplayName: "Twitter",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Twitter",
    },
    {
      lookupValue: "Website",
      lookupDisplayName: "Website",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Website",
    },
    {
      lookupValue: "Youtube",
      lookupDisplayName: "YouTube",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/YouTube",
    },
  ],
  SpaFeatures: [
    {
      lookupValue: "AboveGround",
      lookupDisplayName: "Above Ground",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246509",
    },
    {
      lookupValue: "Bath",
      lookupDisplayName: "Bath",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246510",
    },
    {
      lookupValue: "Community",
      lookupDisplayName: "Community",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246511",
    },
    {
      lookupValue: "Fiberglass",
      lookupDisplayName: "Fiberglass",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246512",
    },
    {
      lookupValue: "Gunite",
      lookupDisplayName: "Gunite",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246513",
    },
    {
      lookupValue: "Heated",
      lookupDisplayName: "Heated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246514",
    },
    {
      lookupValue: "InGround",
      lookupDisplayName: "In Ground",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246515",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246516",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246517",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246518",
    },
  ],
  SpecialLicenses: [
    {
      lookupValue: "BeerWine",
      lookupDisplayName: "Beer/Wine",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246295",
    },
    {
      lookupValue: "ClassH",
      lookupDisplayName: "Class H",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Class+H",
    },
    {
      lookupValue: "Entertainment",
      lookupDisplayName: "Entertainment",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Entertainment",
    },
    {
      lookupValue: "Franchise",
      lookupDisplayName: "Franchise",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246298",
    },
    {
      lookupValue: "Gambling",
      lookupDisplayName: "Gambling",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Gambling",
    },
    {
      lookupValue: "Liquor",
      lookupDisplayName: "Liquor",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Liquor",
    },
    {
      lookupValue: "Liquor5YearsOrLess",
      lookupDisplayName: "Liquor 5 Years Or Less",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Liquor+5+Years+Or+Less",
    },
    {
      lookupValue: "Liquor5YearsOrMore",
      lookupDisplayName: "Liquor 5 Years Or More",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Liquor+5+Years+Or+More",
    },
    {
      lookupValue: "LiquorOffSale",
      lookupDisplayName: "Liquor-Off Sale",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Liquor-Off+Sale",
    },
    {
      lookupValue: "LiquorOnSale",
      lookupDisplayName: "Liquor-On Sale",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Liquor-On+Sale",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246305",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246306",
    },
    {
      lookupValue: "Professional",
      lookupDisplayName: "Professional",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Professional",
    },
  ],
  SpecialListingConditions: [
    {
      lookupValue: "Auction",
      lookupDisplayName: "Auction",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Auction",
    },
    {
      lookupValue: "BankruptcyProperty",
      lookupDisplayName: "Bankruptcy Property",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bankruptcy+Property",
    },
    {
      lookupValue: "HudOwned",
      lookupDisplayName: "HUD Owned",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HUD+Owned",
    },
    {
      lookupValue: "InForeclosure",
      lookupDisplayName: "In Foreclosure",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/In+Foreclosure",
    },
    {
      lookupValue: "NoticeOfDefault",
      lookupDisplayName: "Notice Of Default",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Notice+Of+Default",
    },
    {
      lookupValue: "ProbateListing",
      lookupDisplayName: "Probate Listing",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Probate+Listing",
    },
    {
      lookupValue: "RealEstateOwned",
      lookupDisplayName: "Real Estate Owned",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Real+Estate+Owned",
    },
    {
      lookupValue: "ShortSale",
      lookupDisplayName: "Short Sale",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Short+Sale",
    },
    {
      lookupValue: "Standard",
      lookupDisplayName: "Standard",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Standard",
    },
    {
      lookupValue: "ThirdPartyApproval",
      lookupDisplayName: "Third Party Approval",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Third+Party+Approval",
    },
  ],
  StandardStatus: [
    {
      lookupValue: "Active",
      lookupDisplayName: "Active",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246320",
    },
    {
      lookupValue: "ActiveUnderContract",
      lookupDisplayName: "Active Under Contract",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246321",
    },
    {
      lookupValue: "Canceled",
      lookupDisplayName: "Canceled",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246322",
    },
    {
      lookupValue: "Closed",
      lookupDisplayName: "Closed",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246323",
    },
    {
      lookupValue: "ComingSoon",
      lookupDisplayName: "Coming Soon",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Coming+Soon",
    },
    {
      lookupValue: "Delete",
      lookupDisplayName: "Delete",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Delete",
    },
    {
      lookupValue: "Expired",
      lookupDisplayName: "Expired",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246326",
    },
    {
      lookupValue: "Hold",
      lookupDisplayName: "Hold",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246327",
    },
    {
      lookupValue: "Incomplete",
      lookupDisplayName: "Incomplete",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Incomplete",
    },
    {
      lookupValue: "Pending",
      lookupDisplayName: "Pending",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246329",
    },
    {
      lookupValue: "Withdrawn",
      lookupDisplayName: "Withdrawn",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246330",
    },
  ],
  StateOrProvince: [
    {
      lookupValue: "AB",
      lookupDisplayName: "AB",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AB",
    },
    {
      lookupValue: "AK",
      lookupDisplayName: "AK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/AK",
    },
    {
      lookupValue: "AL",
      lookupDisplayName: "AL",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246334",
    },
    {
      lookupValue: "AR",
      lookupDisplayName: "AR",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246335",
    },
    {
      lookupValue: "AZ",
      lookupDisplayName: "AZ",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246336",
    },
    {
      lookupValue: "BC",
      lookupDisplayName: "BC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/BC",
    },
    {
      lookupValue: "CA",
      lookupDisplayName: "CA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246338",
    },
    {
      lookupValue: "CO",
      lookupDisplayName: "CO",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246339",
    },
    {
      lookupValue: "CT",
      lookupDisplayName: "CT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/CT",
    },
    {
      lookupValue: "DC",
      lookupDisplayName: "DC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/DC",
    },
    {
      lookupValue: "DE",
      lookupDisplayName: "DE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246342",
    },
    {
      lookupValue: "FL",
      lookupDisplayName: "FL",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/FL",
    },
    {
      lookupValue: "GA",
      lookupDisplayName: "GA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246344",
    },
    {
      lookupValue: "HI",
      lookupDisplayName: "HI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/HI",
    },
    {
      lookupValue: "IA",
      lookupDisplayName: "IA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/IA",
    },
    {
      lookupValue: "ID",
      lookupDisplayName: "ID",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246347",
    },
    {
      lookupValue: "IL",
      lookupDisplayName: "IL",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246348",
    },
    {
      lookupValue: "IN",
      lookupDisplayName: "IN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246349",
    },
    {
      lookupValue: "KS",
      lookupDisplayName: "KS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/KS",
    },
    {
      lookupValue: "KY",
      lookupDisplayName: "KY",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246351",
    },
    {
      lookupValue: "LA",
      lookupDisplayName: "LA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246352",
    },
    {
      lookupValue: "MA",
      lookupDisplayName: "MA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246353",
    },
    {
      lookupValue: "MB",
      lookupDisplayName: "MB",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MB",
    },
    {
      lookupValue: "MD",
      lookupDisplayName: "MD",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246355",
    },
    {
      lookupValue: "ME",
      lookupDisplayName: "ME",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246356",
    },
    {
      lookupValue: "MI",
      lookupDisplayName: "MI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/MI",
    },
    {
      lookupValue: "MN",
      lookupDisplayName: "MN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246358",
    },
    {
      lookupValue: "MO",
      lookupDisplayName: "MO",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246359",
    },
    {
      lookupValue: "MS",
      lookupDisplayName: "MS",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246360",
    },
    {
      lookupValue: "MT",
      lookupDisplayName: "MT",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246361",
    },
    {
      lookupValue: "NB",
      lookupDisplayName: "NB",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NB",
    },
    {
      lookupValue: "NC",
      lookupDisplayName: "NC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246363",
    },
    {
      lookupValue: "ND",
      lookupDisplayName: "ND",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ND",
    },
    {
      lookupValue: "NE",
      lookupDisplayName: "NE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246365",
    },
    {
      lookupValue: "NF",
      lookupDisplayName: "NF",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246366",
    },
    {
      lookupValue: "NH",
      lookupDisplayName: "NH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NH",
    },
    {
      lookupValue: "NJ",
      lookupDisplayName: "NJ",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NJ",
    },
    {
      lookupValue: "NM",
      lookupDisplayName: "NM",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NM",
    },
    {
      lookupValue: "NS",
      lookupDisplayName: "NS",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NS",
    },
    {
      lookupValue: "NT",
      lookupDisplayName: "NT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NT",
    },
    {
      lookupValue: "NU",
      lookupDisplayName: "NU",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246372",
    },
    {
      lookupValue: "NV",
      lookupDisplayName: "NV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NV",
    },
    {
      lookupValue: "NY",
      lookupDisplayName: "NY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NY",
    },
    {
      lookupValue: "OH",
      lookupDisplayName: "OH",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/OH",
    },
    {
      lookupValue: "OK",
      lookupDisplayName: "OK",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/OK",
    },
    {
      lookupValue: "ON",
      lookupDisplayName: "ON",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ON",
    },
    {
      lookupValue: "OR",
      lookupDisplayName: "OR",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/OR",
    },
    {
      lookupValue: "PA",
      lookupDisplayName: "PA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246379",
    },
    {
      lookupValue: "PE",
      lookupDisplayName: "PE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246380",
    },
    {
      lookupValue: "QC",
      lookupDisplayName: "QC",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/QC",
    },
    {
      lookupValue: "RI",
      lookupDisplayName: "RI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/RI",
    },
    {
      lookupValue: "SC",
      lookupDisplayName: "SC",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246383",
    },
    {
      lookupValue: "SD",
      lookupDisplayName: "SD",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246384",
    },
    {
      lookupValue: "SK",
      lookupDisplayName: "SK",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246385",
    },
    {
      lookupValue: "TN",
      lookupDisplayName: "TN",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246386",
    },
    {
      lookupValue: "TX",
      lookupDisplayName: "TX",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/TX",
    },
    {
      lookupValue: "UT",
      lookupDisplayName: "UT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/UT",
    },
    {
      lookupValue: "VA",
      lookupDisplayName: "VA",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246389",
    },
    {
      lookupValue: "VI",
      lookupDisplayName: "VI",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246390",
    },
    {
      lookupValue: "VT",
      lookupDisplayName: "VT",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/VT",
    },
    {
      lookupValue: "WA",
      lookupDisplayName: "WA",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/WA",
    },
    {
      lookupValue: "WI",
      lookupDisplayName: "WI",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/WI",
    },
    {
      lookupValue: "WV",
      lookupDisplayName: "WV",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/WV",
    },
    {
      lookupValue: "WY",
      lookupDisplayName: "WY",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/WY",
    },
    {
      lookupValue: "YT",
      lookupDisplayName: "YT",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246396",
    },
  ],
  StreetDirection: [
    {
      lookupValue: "E",
      lookupDisplayName: "E",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/E",
    },
    {
      lookupValue: "N",
      lookupDisplayName: "N",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/N",
    },
    {
      lookupValue: "NE",
      lookupDisplayName: "NE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246400",
    },
    {
      lookupValue: "NW",
      lookupDisplayName: "NW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/NW",
    },
    {
      lookupValue: "S",
      lookupDisplayName: "S",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/S",
    },
    {
      lookupValue: "SE",
      lookupDisplayName: "SE",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246403",
    },
    {
      lookupValue: "SW",
      lookupDisplayName: "SW",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/SW",
    },
    {
      lookupValue: "W",
      lookupDisplayName: "W",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/W",
    },
  ],
  StructureType: [
    {
      lookupValue: "Cabin",
      lookupDisplayName: "Cabin",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246437",
    },
    {
      lookupValue: "Dock",
      lookupDisplayName: "Dock",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246438",
    },
    {
      lookupValue: "Duplex",
      lookupDisplayName: "Duplex",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246439",
    },
    {
      lookupValue: "Flex",
      lookupDisplayName: "Flex",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Flex",
    },
    {
      lookupValue: "HotelMotel",
      lookupDisplayName: "Hotel/Motel",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246441",
    },
    {
      lookupValue: "House",
      lookupDisplayName: "House",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/House",
    },
    {
      lookupValue: "Industrial",
      lookupDisplayName: "Industrial",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246443",
    },
    {
      lookupValue: "ManufacturedHouse",
      lookupDisplayName: "Manufactured House",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manufactured+House",
    },
    {
      lookupValue: "MixedUse",
      lookupDisplayName: "Mixed Use",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246445",
    },
    {
      lookupValue: "MultiFamily",
      lookupDisplayName: "Multi Family",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246446",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246447",
    },
    {
      lookupValue: "Office",
      lookupDisplayName: "Office",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246448",
    },
    {
      lookupValue: "Quadruplex",
      lookupDisplayName: "Quadruplex",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246449",
    },
    {
      lookupValue: "Retail",
      lookupDisplayName: "Retail",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246450",
    },
    {
      lookupValue: "Townhouse",
      lookupDisplayName: "Townhouse",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246451",
    },
    {
      lookupValue: "Triplex",
      lookupDisplayName: "Triplex",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246452",
    },
    {
      lookupValue: "Warehouse",
      lookupDisplayName: "Warehouse",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246453",
    },
  ],
  SyndicateTo: [
    {
      lookupValue: "HomesDotCom",
      lookupDisplayName: "Homes.com",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Homes.com",
    },
    {
      lookupValue: "Listhub",
      lookupDisplayName: "ListHub",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/ListHub",
    },
    {
      lookupValue: "RealtorDotCom",
      lookupDisplayName: "Realtor.com",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Realtor.com",
    },
    {
      lookupValue: "ZillowTrulia",
      lookupDisplayName: "Zillow/Trulia",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246431",
    },
  ],
  TaxStatusCurrent: [
    {
      lookupValue: "Personal",
      lookupDisplayName: "Personal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Personal",
    },
    {
      lookupValue: "PersonalAndReal",
      lookupDisplayName: "Personal And Real",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Personal+And+Real",
    },
    {
      lookupValue: "Real",
      lookupDisplayName: "Real",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Real",
    },
  ],
  TeamMemberType: [
    {
      lookupValue: "AdministrationAssistant",
      lookupDisplayName: "Administration Assistant",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Administration+Assistant",
    },
    {
      lookupValue: "BuyerAgent",
      lookupDisplayName: "Buyer Agent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Buyer+Agent",
    },
    {
      lookupValue: "BuyerAgent",
      lookupDisplayName: "Buyer Agent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Buyer+Agent",
    },
    {
      lookupValue: "LeadManager",
      lookupDisplayName: "Lead Manager",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lead+Manager",
    },
    {
      lookupValue: "ListingAgent",
      lookupDisplayName: "Listing Agent",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Listing+Agent",
    },
    {
      lookupValue: "MarketingAssistant",
      lookupDisplayName: "Marketing Assistant",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Marketing+Assistant",
    },
    {
      lookupValue: "OperationsManager",
      lookupDisplayName: "Operations Manager",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Operations+Manager",
    },
    {
      lookupValue: "TeamLead",
      lookupDisplayName: "Team Lead",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Team+Lead",
    },
    {
      lookupValue: "TeamMember",
      lookupDisplayName: "Team Member",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Team+Member+Lead",
    },
    {
      lookupValue: "TransactionCoordinator",
      lookupDisplayName: "Transaction Coordinator",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Transaction+Coordinator",
    },
  ],
  TeamStatus: [
    {
      lookupValue: "Active",
      lookupDisplayName: "Active",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246521",
    },
    {
      lookupValue: "Inactive",
      lookupDisplayName: "Inactive",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246522",
    },
  ],
  TenantPays: [
    {
      lookupValue: "AllUtilities",
      lookupDisplayName: "All Utilities",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246524",
    },
    {
      lookupValue: "AssociationFees",
      lookupDisplayName: "Association Fees",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246525",
    },
    {
      lookupValue: "CableTv",
      lookupDisplayName: "Cable TV",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246526",
    },
    {
      lookupValue: "CommonAreaMaintenance",
      lookupDisplayName: "Common Area Maintenance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246527",
    },
    {
      lookupValue: "Electricity",
      lookupDisplayName: "Electricity",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246528",
    },
    {
      lookupValue: "ExteriorMaintenance",
      lookupDisplayName: "Exterior Maintenance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246529",
    },
    {
      lookupValue: "Gas",
      lookupDisplayName: "Gas",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246530",
    },
    {
      lookupValue: "GroundsCare",
      lookupDisplayName: "Grounds Care",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246531",
    },
    {
      lookupValue: "HotWater",
      lookupDisplayName: "Hot Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246532",
    },
    {
      lookupValue: "HvacMaintenance",
      lookupDisplayName: "HVAC Maintenance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246533",
    },
    {
      lookupValue: "Insurance",
      lookupDisplayName: "Insurance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246534",
    },
    {
      lookupValue: "JanitorialService",
      lookupDisplayName: "Janitorial Service",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246535",
    },
    {
      lookupValue: "Management",
      lookupDisplayName: "Management",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246536",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246537",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246538",
    },
    {
      lookupValue: "OtherTax",
      lookupDisplayName: "Other Tax",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246539",
    },
    {
      lookupValue: "ParkingFee",
      lookupDisplayName: "Parking Fee",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246540",
    },
    {
      lookupValue: "PestControl",
      lookupDisplayName: "Pest Control",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246541",
    },
    {
      lookupValue: "PoolMaintenance",
      lookupDisplayName: "Pool Maintenance",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246542",
    },
    {
      lookupValue: "Repairs",
      lookupDisplayName: "Repairs",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246543",
    },
    {
      lookupValue: "Roof",
      lookupDisplayName: "Roof",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246544",
    },
    {
      lookupValue: "Security",
      lookupDisplayName: "Security",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246545",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246546",
    },
    {
      lookupValue: "Sewer",
      lookupDisplayName: "Sewer",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246547",
    },
    {
      lookupValue: "SnowRemoval",
      lookupDisplayName: "Snow Removal",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246548",
    },
    {
      lookupValue: "Taxes",
      lookupDisplayName: "Taxes",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246549",
    },
    {
      lookupValue: "Telephone",
      lookupDisplayName: "Telephone",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246550",
    },
    {
      lookupValue: "TrashCollection",
      lookupDisplayName: "Trash Collection",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246551",
    },
    {
      lookupValue: "Water",
      lookupDisplayName: "Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246552",
    },
  ],
  UnitsFurnished: [
    {
      lookupValue: "AllUnits",
      lookupDisplayName: "All Units",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/All+Units",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246595",
    },
    {
      lookupValue: "VariesByUnit",
      lookupDisplayName: "Varies By Unit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Varies+By+Unit",
    },
  ],
  "UnitType[type]Furnished": [
    {
      lookupValue: "Furnished",
      lookupDisplayName: "Furnished",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246598",
    },
    {
      lookupValue: "Partially",
      lookupDisplayName: "Partially",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246599",
    },
    {
      lookupValue: "Unfurnished",
      lookupDisplayName: "Unfurnished",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246600",
    },
  ],
  UnitTypeType: [
    {
      lookupValue: "Apartments",
      lookupDisplayName: "Apartments",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Apartments",
    },
    {
      lookupValue: "Efficiency",
      lookupDisplayName: "Efficiency",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Efficiency",
    },
    {
      lookupValue: "FourBedroomOrMore",
      lookupDisplayName: "4 Bedroom Or More",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/4+Bedroom+Or+More",
    },
    {
      lookupValue: "Loft",
      lookupDisplayName: "Loft",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246608",
    },
    {
      lookupValue: "ManagersUnit",
      lookupDisplayName: "Manager's Unit",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Manager%27s+Unit",
    },
    {
      lookupValue: "OneBedroom",
      lookupDisplayName: "1 Bedroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/1+Bedroom",
    },
    {
      lookupValue: "Penthouse",
      lookupDisplayName: "Penthouse",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Penthouse",
    },
    {
      lookupValue: "Studio",
      lookupDisplayName: "Studio",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246611",
    },
    {
      lookupValue: "ThreeBedroom",
      lookupDisplayName: "3 Bedroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/3+Bedroom",
    },
    {
      lookupValue: "TwoBedroom",
      lookupDisplayName: "2 Bedroom",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/2+Bedroom",
    },
  ],
  Utilities: [
    {
      lookupValue: "CableAvailable",
      lookupDisplayName: "Cable Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cable+Available",
    },
    {
      lookupValue: "CableConnected",
      lookupDisplayName: "Cable Connected",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cable+Connected",
    },
    {
      lookupValue: "CableNotAvailable",
      lookupDisplayName: "Cable Not Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cable+Not+Available",
    },
    {
      lookupValue: "ElectricityAvailable",
      lookupDisplayName: "Electricity Available",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Electricity+Available",
    },
    {
      lookupValue: "ElectricityConnected",
      lookupDisplayName: "Electricity Connected",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Electricity+Connected",
    },
    {
      lookupValue: "ElectricityNotAvailable",
      lookupDisplayName: "Electricity Not Available",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Electricity+Not+Available",
    },
    {
      lookupValue: "NaturalGasAvailable",
      lookupDisplayName: "Natural Gas Available",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Natural+Gas+Available",
    },
    {
      lookupValue: "NaturalGasConnected",
      lookupDisplayName: "Natural Gas Connected",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Natural+Gas+Connected",
    },
    {
      lookupValue: "NaturalGasNotAvailable",
      lookupDisplayName: "Natural Gas Not Available",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Natural+Gas+Not+Available",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246579",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246580",
    },
    {
      lookupValue: "PhoneAvailable",
      lookupDisplayName: "Phone Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Phone+Available",
    },
    {
      lookupValue: "PhoneConnected",
      lookupDisplayName: "Phone Connected",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Phone+Connected",
    },
    {
      lookupValue: "PhoneNotAvailable",
      lookupDisplayName: "Phone Not Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Phone+Not+Available",
    },
    {
      lookupValue: "Propane",
      lookupDisplayName: "Propane",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Propane",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246585",
    },
    {
      lookupValue: "SewerAvailable",
      lookupDisplayName: "Sewer Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sewer+Available",
    },
    {
      lookupValue: "SewerConnected",
      lookupDisplayName: "Sewer Connected",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sewer+Connected",
    },
    {
      lookupValue: "SewerNotAvailable",
      lookupDisplayName: "Sewer Not Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Sewer+Not+Available",
    },
    {
      lookupValue: "UndergroundUtilities",
      lookupDisplayName: "Underground Utilities",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Underground+Utilities",
    },
    {
      lookupValue: "WaterAvailable",
      lookupDisplayName: "Water Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Available",
    },
    {
      lookupValue: "WaterConnected",
      lookupDisplayName: "Water Connected",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Connected",
    },
    {
      lookupValue: "WaterNotAvailable",
      lookupDisplayName: "Water Not Available",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Water+Not+Available",
    },
  ],
  Vegetation: [
    {
      lookupValue: "Brush",
      lookupDisplayName: "Brush",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Brush",
    },
    {
      lookupValue: "Cleared",
      lookupDisplayName: "Cleared",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cleared",
    },
    {
      lookupValue: "Crops",
      lookupDisplayName: "Crop(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246678",
    },
    {
      lookupValue: "Grassed",
      lookupDisplayName: "Grassed",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Grassed",
    },
    {
      lookupValue: "HeavilyWooded",
      lookupDisplayName: "Heavily Wooded",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Heavily+Wooded",
    },
    {
      lookupValue: "NaturalState",
      lookupDisplayName: "Natural State",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Natural+State",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246682",
    },
    {
      lookupValue: "PartiallyWooded",
      lookupDisplayName: "Partially Wooded",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Partially+Wooded",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246684",
    },
    {
      lookupValue: "Wooded",
      lookupDisplayName: "Wooded",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wooded",
    },
  ],
  View: [
    {
      lookupValue: "Bay",
      lookupDisplayName: "Bay",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Bay",
    },
    {
      lookupValue: "Beach",
      lookupDisplayName: "Beach",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Beach",
    },
    {
      lookupValue: "Bridges",
      lookupDisplayName: "Bridge(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246689",
    },
    {
      lookupValue: "Canal",
      lookupDisplayName: "Canal",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Canal",
    },
    {
      lookupValue: "Canyon",
      lookupDisplayName: "Canyon",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Canyon",
    },
    {
      lookupValue: "City",
      lookupDisplayName: "City",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246692",
    },
    {
      lookupValue: "CityLights",
      lookupDisplayName: "City Lights",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/City+Lights",
    },
    {
      lookupValue: "CreekStream",
      lookupDisplayName: "Creek/Stream",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246694",
    },
    {
      lookupValue: "Desert",
      lookupDisplayName: "Desert",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Desert",
    },
    {
      lookupValue: "Downtown",
      lookupDisplayName: "Downtown",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Downtown",
    },
    {
      lookupValue: "Forest",
      lookupDisplayName: "Forest",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Forest",
    },
    {
      lookupValue: "Garden",
      lookupDisplayName: "Garden",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246698",
    },
    {
      lookupValue: "GolfCourse",
      lookupDisplayName: "Golf Course",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246699",
    },
    {
      lookupValue: "Hills",
      lookupDisplayName: "Hills",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Hills",
    },
    {
      lookupValue: "Lake",
      lookupDisplayName: "Lake",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246701",
    },
    {
      lookupValue: "Marina",
      lookupDisplayName: "Marina",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246702",
    },
    {
      lookupValue: "Meadow",
      lookupDisplayName: "Meadow",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246703",
    },
    {
      lookupValue: "Mountains",
      lookupDisplayName: "Mountain(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246704",
    },
    {
      lookupValue: "Neighborhood",
      lookupDisplayName: "Neighborhood",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Neighborhood",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246706",
    },
    {
      lookupValue: "Ocean",
      lookupDisplayName: "Ocean",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ocean",
    },
    {
      lookupValue: "Orchard",
      lookupDisplayName: "Orchard",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246708",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246709",
    },
    {
      lookupValue: "Panoramic",
      lookupDisplayName: "Panoramic",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Panoramic",
    },
    {
      lookupValue: "ParkGreenbelt",
      lookupDisplayName: "Park/Greenbelt",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246711",
    },
    {
      lookupValue: "Pasture",
      lookupDisplayName: "Pasture",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246712",
    },
    {
      lookupValue: "Pond",
      lookupDisplayName: "Pond",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246713",
    },
    {
      lookupValue: "Pool",
      lookupDisplayName: "Pool",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246714",
    },
    {
      lookupValue: "Ridge",
      lookupDisplayName: "Ridge",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ridge",
    },
    {
      lookupValue: "River",
      lookupDisplayName: "River",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246716",
    },
    {
      lookupValue: "Rural",
      lookupDisplayName: "Rural",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Rural",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246718",
    },
    {
      lookupValue: "Skyline",
      lookupDisplayName: "Skyline",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Skyline",
    },
    {
      lookupValue: "Territorial",
      lookupDisplayName: "Territorial",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Territorial",
    },
    {
      lookupValue: "TreesWoods",
      lookupDisplayName: "Trees/Woods",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246721",
    },
    {
      lookupValue: "Valley",
      lookupDisplayName: "Valley",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Valley",
    },
    {
      lookupValue: "Vineyard",
      lookupDisplayName: "Vineyard",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246723",
    },
    {
      lookupValue: "Water",
      lookupDisplayName: "Water",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246724",
    },
  ],
  WaterfrontFeatures: [
    {
      lookupValue: "BeachAccess",
      lookupDisplayName: "Beach Access",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246624",
    },
    {
      lookupValue: "BeachFront",
      lookupDisplayName: "Beach Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Beach+Front",
    },
    {
      lookupValue: "CanalAccess",
      lookupDisplayName: "Canal Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Canal+Access",
    },
    {
      lookupValue: "CanalFront",
      lookupDisplayName: "Canal Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Canal+Front",
    },
    {
      lookupValue: "Creek",
      lookupDisplayName: "Creek",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Creek",
    },
    {
      lookupValue: "Lagoon",
      lookupDisplayName: "Lagoon",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lagoon",
    },
    {
      lookupValue: "Lake",
      lookupDisplayName: "Lake",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246630",
    },
    {
      lookupValue: "LakeFront",
      lookupDisplayName: "Lake Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lake+Front",
    },
    {
      lookupValue: "LakePrivileges",
      lookupDisplayName: "Lake Privileges",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Lake+Privileges",
    },
    {
      lookupValue: "NavigableWater",
      lookupDisplayName: "Navigable Water",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Navigable+Water",
    },
    {
      lookupValue: "OceanAccess",
      lookupDisplayName: "Ocean Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ocean+Access",
    },
    {
      lookupValue: "OceanFront",
      lookupDisplayName: "Ocean Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Ocean+Front",
    },
    {
      lookupValue: "Pond",
      lookupDisplayName: "Pond",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246636",
    },
    {
      lookupValue: "RiverAccess",
      lookupDisplayName: "River Access",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/River+Access",
    },
    {
      lookupValue: "RiverFront",
      lookupDisplayName: "River Front",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/River+Front",
    },
    {
      lookupValue: "Seawall",
      lookupDisplayName: "Seawall",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Seawall",
    },
    {
      lookupValue: "Stream",
      lookupDisplayName: "Stream",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Stream",
    },
    {
      lookupValue: "Waterfront",
      lookupDisplayName: "Waterfront",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246641",
    },
  ],
  WaterSource: [
    {
      lookupValue: "Cistern",
      lookupDisplayName: "Cistern",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Cistern",
    },
    {
      lookupValue: "None",
      lookupDisplayName: "None",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246615",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246616",
    },
    {
      lookupValue: "Private",
      lookupDisplayName: "Private",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246617",
    },
    {
      lookupValue: "Public",
      lookupDisplayName: "Public",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246618",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246619",
    },
    {
      lookupValue: "SharedWell",
      lookupDisplayName: "Shared Well",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shared+Well",
    },
    {
      lookupValue: "Spring",
      lookupDisplayName: "Spring",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Spring",
    },
    {
      lookupValue: "Well",
      lookupDisplayName: "Well",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Well",
    },
  ],
  WindowFeatures: [
    {
      lookupValue: "AluminumFrames",
      lookupDisplayName: "Aluminum Frames",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Aluminum+Frames",
    },
    {
      lookupValue: "BayWindows",
      lookupDisplayName: "Bay Window(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246644",
    },
    {
      lookupValue: "Blinds",
      lookupDisplayName: "Blinds",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Blinds",
    },
    {
      lookupValue: "DisplayWindows",
      lookupDisplayName: "Display Window(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246646",
    },
    {
      lookupValue: "DoublePaneWindows",
      lookupDisplayName: "Double Pane Windows",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Double+Pane+Windows",
    },
    {
      lookupValue: "Drapes",
      lookupDisplayName: "Drapes",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Drapes",
    },
    {
      lookupValue: "EnergyStarQualifiedWindows",
      lookupDisplayName: "ENERGY STAR Qualified Windows",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/ENERGY+STAR+Qualified+Windows",
    },
    {
      lookupValue: "GardenWindows",
      lookupDisplayName: "Garden Window(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246650",
    },
    {
      lookupValue: "InsulatedWindows",
      lookupDisplayName: "Insulated Windows",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Insulated+Windows",
    },
    {
      lookupValue: "LowEmissivityWindows",
      lookupDisplayName: "Low Emissivity Windows",
      wikiPageURL:
        "https://ddwiki.reso.org/display/DDW17/Low+Emissivity+Windows",
    },
    {
      lookupValue: "PlantationShutters",
      lookupDisplayName: "Plantation Shutters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Plantation+Shutters",
    },
    {
      lookupValue: "Screens",
      lookupDisplayName: "Screens",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Screens",
    },
    {
      lookupValue: "Shutters",
      lookupDisplayName: "Shutters",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Shutters",
    },
    {
      lookupValue: "Skylights",
      lookupDisplayName: "Skylight(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246656",
    },
    {
      lookupValue: "SolarScreens",
      lookupDisplayName: "Solar Screens",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Solar+Screens",
    },
    {
      lookupValue: "StormWindows",
      lookupDisplayName: "Storm Window(s)",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246658",
    },
    {
      lookupValue: "TintedWindows",
      lookupDisplayName: "Tinted Windows",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Tinted+Windows",
    },
    {
      lookupValue: "TriplePaneWindows",
      lookupDisplayName: "Triple Pane Windows",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Triple+Pane+Windows",
    },
    {
      lookupValue: "WindowCoverings",
      lookupDisplayName: "Window Coverings",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Window+Coverings",
    },
    {
      lookupValue: "WindowTreatments",
      lookupDisplayName: "Window Treatments",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Window+Treatments",
    },
    {
      lookupValue: "WoodFrames",
      lookupDisplayName: "Wood Frames",
      wikiPageURL: "https://ddwiki.reso.org/display/DDW17/Wood+Frames",
    },
  ],
  YearBuiltSource: [
    {
      lookupValue: "Appraiser",
      lookupDisplayName: "Appraiser",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246666",
    },
    {
      lookupValue: "Assessor",
      lookupDisplayName: "Assessor",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246667",
    },
    {
      lookupValue: "Builder",
      lookupDisplayName: "Builder",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246668",
    },
    {
      lookupValue: "Estimated",
      lookupDisplayName: "Estimated",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246669",
    },
    {
      lookupValue: "Other",
      lookupDisplayName: "Other",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246670",
    },
    {
      lookupValue: "Owner",
      lookupDisplayName: "Owner",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246671",
    },
    {
      lookupValue: "PublicRecords",
      lookupDisplayName: "Public Records",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246672",
    },
    {
      lookupValue: "SeeRemarks",
      lookupDisplayName: "See Remarks",
      wikiPageURL:
        "https://ddwiki.reso.org/pages/viewpage.action?pageId=29246673",
    },
  ],
};

module.exports = {
  lookupMap,
};
