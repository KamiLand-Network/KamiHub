# What are Actions?

Actions are a command-like feature in KamiHub that allows server administrators to quickly implement custom functionalities.

Its format looks like this:
- `[player]say Hello`
- `[message]<green>You are so smart!`

> Looks simple, doesn't it?

As you can see, each action typically consists of two parts: **prefix** and **value**.
The **prefix** specifies the purpose of each action, and the **value** has different meanings and effects depending on the prefix.
For `[player]say Hello`, the prefix is `[player]`, and the value is `say Hello`.

## Official Actions

To facilitate administrators in implementing custom features, KamiHub currently provides the following actions:
- `[player]`: Execute command as player, value is the command content
- `[console]`: Execute command as console, value is the command content
- `[message]`: Send message to player, value is the message content
- `[broadcast]`: Broadcast message to players, value is the message content

For actions like `[message]`, `[broadcast]`, they are used to send messages to players, so their values are in MiniMessage format and support PAPI placeholder parsing.

## Usage of Actions

Currently, KamiHub only executes actions in the player agreement module after the player agrees or rejects the agreement.
In future versions, KamiHub will gradually support more actions and open APIs for developers to register custom Actions.

## Some Misconceptions

Users accustomed to writing YAML configuration files often add a space after the colon to ensure correct format.
`key: value`
However, there should be no space after the prefix of an action, unless you want to add a space at the beginning of the message text.
`[action]value`

If you use `[message] Im a message!`, the message the player actually receives is: ` Im a message!` (with a leading space)
