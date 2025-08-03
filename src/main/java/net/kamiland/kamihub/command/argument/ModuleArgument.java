package net.kamiland.kamihub.command.argument;

import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import net.kamiland.kamihub.manager.ModuleManager;
import net.kamiland.kamihub.module.Module;
import org.bukkit.command.CommandSender;

public class ModuleArgument extends ArgumentResolver<CommandSender, Module> {

    private final ModuleManager moduleManager;

    public ModuleArgument(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    @Override
    protected ParseResult<Module> parse(Invocation<CommandSender> invocation, Argument<Module> context, String argument) {
        if (moduleManager.isModuleExist(argument)) {
            Module module = moduleManager.getModule(argument); assert module != null;
            return ParseResult.success(module);
        }
        return ParseResult.success(null);
    }

    @Override
    public SuggestionResult suggest(
            Invocation<CommandSender> invocation,
            Argument<Module> argument,
            SuggestionContext context
    ) {
        return moduleManager.getModules().values().stream()
                .map(Module::getName)
                .collect(SuggestionResult.collector());
    }

}
