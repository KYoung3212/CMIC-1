import React from 'react';
import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayTable from '@clayui/table';
import {ClayTooltipProvider} from '@clayui/tooltip';
import NumberFormat from 'react-number-format';

const Navigate = (cmicAccountEntryId) => {
  Liferay.Util.navigate('account-details?cmicAccountEntryId=' + cmicAccountEntryId);
}

const spritemap = Liferay.ThemeDisplay.getPathThemeImages() + '/clay/icons.svg';

const AccountList = (props) => {

  if (props.isLoading) {
    return (<ClayLoadingIndicator />);
  }
  else {
    return (
      <React.Fragment>
        <ClayTable>
          <ClayTable.Head>
            <ClayTable.Row>
              <ClayTable.Cell expanded headingCell>
                {Liferay.Language.get('name')}
              </ClayTable.Cell>

              <ClayTable.Cell expanded headingCell align="center" className="table-cell-expand-smallest">
                {Liferay.Language.get('in-force-policies')}
              </ClayTable.Cell>

              <ClayTable.Cell headingCell align="right" className="table-cell-ws-nowrap">
                <ClayTooltipProvider delay="100">
                  <ClayIcon
                    className="text-secondary mr-2"
                    data-tooltip-align="bottom-right"
                    symbol="info-circle-open"
                    spritemap={spritemap}
                    title={Liferay.Language.get('this-value-is-an-estimate')}
                  />
                </ClayTooltipProvider>
                {Liferay.Language.get('policy-premium')}
              </ClayTable.Cell>

            </ClayTable.Row>
          </ClayTable.Head>
          <ClayTable.Body>
            {props.accountsList.map((account, index) => (
              <ClayTable.Row
                key={index}
                className="cursor-pointer"
                onClick={() => Navigate(account.cmicAccountEntryId)}>
                <ClayTable.Cell>
                  <h5 className="font-weight-bold mb-0">{account.accountName}</h5>
                  <small className="text-muted">#{account.accountNumber}</small>
                </ClayTable.Cell>
                <ClayTable.Cell align="center">{account.numInForcePolicies}</ClayTable.Cell>
                <ClayTable.Cell align="right" className="h3 font-weight-bold">
                  <NumberFormat decimalScale={2} displayType={'text'} fixedDecimalScale={true} prefix={'$'} thousandSeparator={true} value={account.totalBilledPremium} />
                </ClayTable.Cell>
              </ClayTable.Row>
            ))}
          </ClayTable.Body>
        </ClayTable>
        <div className="pt-2 px-2"><a href="accounts" className="link-action">{Liferay.Language.get('see-all-accounts')}</a></div>
      </React.Fragment>
    );
  }
};

export default AccountList;