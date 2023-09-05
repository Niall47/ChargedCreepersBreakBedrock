## Charged creepers break bedrock

When charged creepers explode, they break bedrock.

### Configuration
You need a config.yml file in your plugins folder if you want to change the default value which is 30.
The file should look like this:

    ├── plugins
    │    │
    │    ├── ChargedCreepersBreakBedrock
    │    │  └── config.yml
    │    └── ChargedCreepersBreakBedrock.jar

The config.yml file should look like this:
```yaml
max_bedrock_break: 1
```


## Commands
    /summon creeper ~ ~ ~ {powered:1}