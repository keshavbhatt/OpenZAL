/*
 * ZAL - The abstraction layer for Zimbra.
 * Copyright (C) 2016 ZeXtras S.r.l.
 *
 * This file is part of ZAL.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, version 2 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ZAL. If not, see <http://www.gnu.org/licenses/>.
 */

package org.openzal.zal.soap;

import org.jetbrains.annotations.NotNull;
import org.openzal.zal.AccountQuotaInfo;
import org.openzal.zal.ZimbraListWrapper;
import com.zimbra.cs.service.admin.GetQuotaUsage;
import com.zimbra.soap.admin.message.GetQuotaUsageResponse;

import java.util.List;

public class GetQuotaResponse
{
  @NotNull private final GetQuotaUsageResponse mGetQuotaUsageResponse;

  public static final String SORT_TOTAL_USED   = GetQuotaUsage.SORT_TOTAL_USED;
  public static final String SORT_QUOTA_LIMIT  = GetQuotaUsage.SORT_QUOTA_LIMIT;
  public static final String SORT_PERCENT_USED = GetQuotaUsage.SORT_PERCENT_USED;
  public static final String SORT_ACCOUNT      = GetQuotaUsage.SORT_ACCOUNT;

  protected GetQuotaResponse(Object getQuotaUsageResponse)
  {
    mGetQuotaUsageResponse = (GetQuotaUsageResponse) getQuotaUsageResponse;
  }

  public List<AccountQuotaInfo> getAccountQuotas()
  {
    return ZimbraListWrapper.wrapAccountQuotaInfos(mGetQuotaUsageResponse.getAccountQuotas());
  }
}
