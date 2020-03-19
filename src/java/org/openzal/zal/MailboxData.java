package org.openzal.zal;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class MailboxData
{
  private final Integer mId;
  private final int mSchemaGroupId;
  private final String mAccountId;
  private final Short mIndexVolumeId;

  public MailboxData(@Nonnull Integer mailboxid)
  {
    this(
      mailboxid,
      (short) (((mailboxid - 1) % 100) + 1),
      null,
      null
    );
  }

  public MailboxData(@Nonnull String accountId)
  {
    this(
      null,
      -1,
      accountId,
      null
    );
  }

  public MailboxData(
    int mailboxId,
    @Nonnull String accountId
  )
  {
    this(
      mailboxId,
      (short) (((mailboxId - 1) % 100) + 1),
      accountId,
      null
    );
  }

  public MailboxData(
    Integer id,
    int schemaGroupId,
    String accountId,
    Short indexVolumeId
  )
  {
    mId = id;
    mSchemaGroupId = schemaGroupId;
    mAccountId = accountId;
    mIndexVolumeId = indexVolumeId;
  }

  public int getId()
  {
    if( mId == null )
    {
      throw new UnsupportedOperationException();
    }
    return mId;
  }

  public int getSchemaGroupId()
  {
    return mSchemaGroupId;
  }

  @Nullable
  public String getAccountId()
  {
    return mAccountId;
  }

  @Override
  public boolean equals(Object o)
  {
    if( this == o )
    {
      return true;
    }
    if( o == null || getClass() != o.getClass() )
    {
      return false;
    }
    MailboxData that = (MailboxData) o;
    if(mId != null && that.mId != null && !Objects.equals(mId, that.mId))
    {
      return false;
    }
    if(mAccountId != null && that.mAccountId != null && !Objects.equals(mAccountId, that.mAccountId))
    {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(mId, mAccountId);
  }

  @Nullable
  public Short getIndexVolumeId()
  {
    return mIndexVolumeId;
  }

  public boolean hasContent()
  {
    return true;
  }

  public static MailboxData empty()
  {
    return new MailboxData(-1)
    {
      @Override
      public boolean hasContent()
      {
        return false;
      }
    };
  }
}
