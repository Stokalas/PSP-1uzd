public class AccountCreator {
    private AccountValidator accountValidator;
    private AccountRepository accountRepository;

    public AccountCreator(String connString, ValidationSettings validationSettings) {
        this.accountValidator = new AccountValidator(validationSettings);
        this.accountRepository = new AccountRepository(connString);
    }

    public int createAccount(AccountModel accountModel) {
        int validationCode = accountValidator.validateAccount(accountModel);
        return validationCode;
    }
}
