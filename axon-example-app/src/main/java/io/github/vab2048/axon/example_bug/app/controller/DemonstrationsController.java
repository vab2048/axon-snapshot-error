package io.github.vab2048.axon.example_bug.app.controller;

import io.github.vab2048.axon.example_bug.message_api.command.AccountCommandMessageAPI.CreateNewAccountCommand;
import io.github.vab2048.axon.example_bug.message_api.command.AccountCommandMessageAPI.CreditAccountCommand;
import io.github.vab2048.axon.example_bug.message_api.command.AccountCommandMessageAPI.DebitAccountCommand;
import org.apache.commons.lang3.RandomUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DemonstrationsController implements Demonstrations {
    private static final Logger log = LoggerFactory.getLogger(DemonstrationsController.class);

    private final CommandGateway commandGateway;

    public DemonstrationsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    private Object nextCreditOrDebitCommand(UUID accountId, UUID paymentId) {
        // Return either a credit or debit account command randomly...
        var amount = 10L; // Just use this for an easy amount.
        return RandomUtils.nextBoolean() ? new DebitAccountCommand(accountId, paymentId, amount)
                : new CreditAccountCommand(accountId, paymentId, amount);
    }

    /**
     * This endpoint is for illustrating the snapshotting of an aggregate.
     * In our case the 'account' aggregate will be snapshotted based on the
     * configuration we have defined (after every 3 events).
     *
     * So we will issue 10 commands to see the snapshotting occur three times.
     * To view the snapshotting - inspect the DB.
     */
    @Override
    public void triggerAccountSnapshot() {
        var accountId = UUID.randomUUID();

        // The credit/debit account commands requires a payment ID. We are not making payments (to make things
        // simpler) for this scenario, so we will just use a one time generated paymentID for all commands.
        var paymentId = UUID.randomUUID();

        // Create 10 commands:
        var command01 = new CreateNewAccountCommand(accountId);
        var command02 = nextCreditOrDebitCommand(accountId, paymentId);
        var command03 = nextCreditOrDebitCommand(accountId, paymentId);
        var command04 = nextCreditOrDebitCommand(accountId, paymentId);
        var command05 = nextCreditOrDebitCommand(accountId, paymentId);
        var command06 = nextCreditOrDebitCommand(accountId, paymentId);
        var command07 = nextCreditOrDebitCommand(accountId, paymentId);
        var command08 = nextCreditOrDebitCommand(accountId, paymentId);
        var command09 = nextCreditOrDebitCommand(accountId, paymentId);
        var command10 = nextCreditOrDebitCommand(accountId, paymentId);

        // Issue the commands:
        log.debug("Issuing command 1/10: {}", command01);
        commandGateway.sendAndWait(command01);
        log.debug("Issuing command 2/10: {}", command02);
        commandGateway.sendAndWait(command02);
        log.debug("Issuing command 3/10: {}", command03);
        commandGateway.sendAndWait(command03);
        log.debug("Issuing command 4/10: {}", command04);
        commandGateway.sendAndWait(command04);
        log.debug("Issuing command 5/10: {}", command05);
        commandGateway.sendAndWait(command05);
        log.debug("Issuing command 6/10: {}", command06);
        commandGateway.sendAndWait(command06);
        log.debug("Issuing command 7/10: {}", command07);
        commandGateway.sendAndWait(command07);
        log.debug("Issuing command 8/10: {}", command08);
        commandGateway.sendAndWait(command08);
        log.debug("Issuing command 9/10: {}", command09);
        commandGateway.sendAndWait(command09);
        log.debug("Issuing command 10/10: {}", command10);
        commandGateway.sendAndWait(command10);

        // Now inspect the DB for snapshots...
    }

}
