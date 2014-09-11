package org.openzal.zal;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.openzal.zal.exceptions.ZimbraException;
import com.zimbra.cs.account.*;
import com.zimbra.cs.account.accesscontrol.RightModifier;
import com.zimbra.cs.account.auth.AuthContext;

/* $if MajorZimbraVersion >= 8 $ */
/* $if ZimbraVersion >= 8.0.6 $*/
import com.zimbra.soap.admin.type.GranteeSelector.GranteeBy;
/* $else$
 import com.zimbra.common.account.Key.GranteeBy;
/* $endif$ */

import com.zimbra.common.account.Key;
import com.zimbra.soap.admin.type.CacheEntryType;
import com.zimbra.soap.type.TargetBy;
import org.mockito.Mockito;
/* $else$
import org.mockito.Mockito;
import com.zimbra.cs.account.Provisioning.CacheEntryType;
$endif$ */

public class ProvisioningSimulator extends Provisioning
{
  private Map<String, Domain> mDomainMap;
  private Map<String, Account> mAccountMap;

  /*************** Simulator methods ********************/
  public ProvisioningSimulator()
  {
    super(null);
    mDomainMap = new HashMap<String, Domain>();
    mAccountMap = new HashMap<String, Account>();
  }

  public void addDomain(String domain)
  {
    if (mDomainMap.containsKey(domain))
    {
      return;
    }
    mDomainMap.put(domain, createFakeDomain(domain));
  }

  public void addUserWithAliases(String address, List<String> aliases)
  {
    addUser(address);
    Account account = mAccountMap.get(address);

    for (String alias : aliases)
    {
      mAccountMap.put(alias, account);
    }
  }

  public Account addUser(String address)
  {
    return addUser(address, address);
  }

  public Account addUser(String name, String address)
  {
    if (mAccountMap.containsKey(address))
    {
      return mAccountMap.get(address);
    }

    int domainIdx = address.indexOf('@');
    if (domainIdx == -1)
    {
      throw new RuntimeException("Invalid address " + address);
    }

    String domain = address.substring(domainIdx + 1);
    addDomain(domain);

    Account account = createFakeAccount(name, address);
    mAccountMap.put(address, account);

    return account;
  }

  public Domain createFakeDomain(String domain)
  {
    Map<String,Object> attrs = new HashMap<String, Object>();
    Map<String, Object> defaults = new HashMap<String, Object>();

    return new Domain(domain,domain,attrs,defaults,this);
  }

  public Account createFakeAccount(String accountStr)
  {
    return createFakeAccount(accountStr, accountStr);
  }

  public Account createFakeAccount(String name, String accountStr)
  {
    return createFakeAccount(name,accountStr,Collections.<String,Object>emptyMap());
  }

  public Account createFakeAccount(String name, String accountStr, Map<String,Object> extraAttr )
  {
    if( name == null ) {
      name = "mockito";
    }

    if( accountStr == null ) {
      accountStr = "mockito@example.com";
    }

    Map<String,Object> attrs = new HashMap<String, Object>();
    attrs.put(com.zimbra.cs.account.Provisioning.A_mail, accountStr);

    Map<String, Object> defaults = new HashMap<String, Object>();
    defaults.put(com.zimbra.cs.account.Provisioning.A_zimbraAccountStatus,
                 com.zimbra.cs.account.Provisioning.ACCOUNT_STATUS_ACTIVE);
    defaults.put(com.zimbra.cs.account.Provisioning.A_displayName,
                 accountStr);

    return new AccountSimulator(
      name,
      accountStr,
      attrs,
      defaults,
      this
    );
  }

  public Account getAccountByName(String accountStr)
  {
    return mAccountMap.get(accountStr);
  }

  public Domain getDomainByName( String domain )
  {
    return mDomainMap.get(domain);
  }

  public boolean onLocalServer(Account userAccount)
    throws ZimbraException {
    return true;
  }

  public com.zimbra.cs.account.Provisioning toZimbra()
  {
    return Mockito.mock(com.zimbra.cs.account.Provisioning.class);
  }

  /******************************************************/

  public Account getZimbraUser()
    throws ZimbraException {
    throw new RuntimeException("Provisioning method not implemented.");
  }

  public OperationContext createZContext()
    throws ZimbraException {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public DistributionList getDistributionListById(String id)
    throws ZimbraException {
/* $if MajorZimbraVersion >= 8 $ */
    throw new RuntimeException("Provisioning method not implemented");
/* $else$
    throw new RuntimeException("Provisioning method not implemented");
   $endif$ */
  }

  public DistributionList getDistributionListByName(String name)
    throws ZimbraException {
/* $if MajorZimbraVersion >= 8 $ */
    throw new RuntimeException("Provisioning method not implemented");
/* $else$
    throw new RuntimeException("Provisioning method not implemented");
   $endif$ */
  }

  public void visitAllAccounts( NamedEntry.Visitor visitor )
      throws ZimbraException
  {
    /* $if MajorZimbraVersion >= 8 $ */
    throw new RuntimeException("Provisioning method not implemented");
    /* $else$
    throw new RuntimeException("Provisioning method not implemented");
    $endif$ */
  }

  public void visitAllDomains(NamedEntry.Visitor visitor)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public void visitDomain(NamedEntry.Visitor visitor, Domain domain)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public void authAccount(Account account, String password, AuthContext.Protocol protocol, Map<String, Object> context)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public Account getAccountById(String accountId)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented("+accountId+")");
  }

  public Server getLocalServer()
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  /* $if MajorZimbraVersion >= 8 $ */
  public List<NamedEntry> searchAccountsOnServer(Server localServer, SearchAccountsOptions searchOpts)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }
  /* $endif$ */

  public List<Domain> getAllDomains()
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  @NotNull
  public Zimlet getZimlet(String zimletName)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public Domain getDomainById(String domainId)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public List<DistributionList> getAllDistributionLists(Domain domain)
    throws ZimbraException
  {
    return Collections.emptyList();
  }

  public Cos getCosById(String cosId)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public List<Cos> getAllCos()
      throws ZimbraException
  {
    return Collections.emptyList();
  }


  public Cos getCosByName(String cosStr)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

/* $if MajorZimbraVersion < 8 $
  public List<NamedEntry> searchAccounts(String query, String returnAttrs[], String sortAttr, boolean sortAscending, int flags) throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }
 $endif$ */

  /* $if MajorZimbraVersion >= 8 $ */
  public List<Account> searchDirectory(SearchDirectoryOptions opts)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public void searchDirectory(SearchDirectoryOptions opts, NamedEntry.Visitor visitor)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public DistributionList get(Key.DistributionListBy id, String dlStr)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }
  /* $endif$ */

  public List<Account> getAllAdminAccounts()
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public List<Account> getAllAccounts(Domain domain)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public List<Server> getAllServers()
      throws ZimbraException
  {
    return Collections.singletonList(
      new Server(
        "localhost",
        "",
        new HashMap<String, Object>(),
        new HashMap<String, Object>(),
        new Provisioning(mProvisioning)
      ));
  }

  /* $if MajorZimbraVersion >= 8 $ */
  public List<CalendarResource> getAllCalendarResources(Domain domain)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }
  /* $else$
  public List getAllCalendarResources(Domain domain)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }
  $endif$ */

  public List<Zimlet> listAllZimlets()
      throws ZimbraException
  {
    return Collections.emptyList();
  }

  public List<XMPPComponent> getAllXMPPComponents()
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public GlobalGrant getGlobalGrant()
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public Config getConfig()
      throws ZimbraException
  {
    return new Config(
      new HashMap<String, Object>(){{
        put("key", "value");
      }},
      new Provisioning(mProvisioning)
    );
  }

  /* $if MajorZimbraVersion >= 8 $ */
  public List<UCService> getAllUCServices()
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }
  /* $endif$ */

  public CalendarResource getCalendarResourceByName(String resourceName)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public CalendarResource getCalendarResourceById(String resourceId)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public Domain createDomain(String currentDomainName, HashMap<String, Object> stringObjectHashMap)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public Cos createCos(String cosname, HashMap<String, Object> stringObjectHashMap)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public DistributionList createDistributionList(String dlistName, HashMap<String, Object> stringObjectHashMap)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public Account createCalendarResource(String dstAccount, String newPassword, Map<String, Object> attrs)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public Account createAccount(String dstAccount, String newPassword, Map<String, Object> attrs)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public void modifyIdentity(Account newAccount, String identityName, Map<String, Object> newAttrs)
      throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  /* $if MajorZimbraVersion == 8 $ */
  public void grantRight(String targetType, TargetBy targetBy, String target,
                         String granteeType, GranteeBy granteeBy, String grantee, String secret,
                         String right, RightModifier rightModifier) throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }
  /* $endif$ */

  public Domain getDomain(Account account)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public void flushCache(CacheEntryType account, com.zimbra.cs.account.Provisioning.CacheEntry[] cacheEntries)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  public CountAccountResult countAccount(Domain domain)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }


  public Server getServer(Account acct)
    throws ZimbraException
  {
    throw new RuntimeException("Provisioning method not implemented");
  }

  /******************************************************/
  public static class AccountSimulator extends Account
  {
    public AccountSimulator(String name,
                            String id,
                            Map<String, Object> attrs,
                            Map<String, Object> defaults,
                            Provisioning prov)
    {
      super(name, id, attrs, defaults, prov);
    }

    @NotNull
    @Override
    public String getId()
    {
      return getName();
    }

    private void setAttr( String key, Object value )
    {
      Map<String,Object> attrs = getAttrs();
      attrs.put(key, value);
      setAttrs(attrs);
    }

    @Override
    public void setIsDelegatedAdminAccount(boolean value)
    {
      setAttr(com.zimbra.cs.account.Provisioning.A_zimbraIsDelegatedAdminAccount, String.valueOf(value).toUpperCase());
    }


    @Override
    public String getDisplayName()
    {
      return getName();
    }

    @Override
    public void authAccount(String password, Protocol proto)
    {}

    @Override
    public String getAccountStatus(Provisioning prov)
    {
      return "test status";
    }
  }

  public Cos getCOS(Account acct) throws ZimbraException
  {
    return new Cos("test", "id", new HashMap<String, Object>(), this);
  }
}
