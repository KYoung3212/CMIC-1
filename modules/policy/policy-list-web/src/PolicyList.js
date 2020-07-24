import React, {useState} from 'react';
import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayTable from '@clayui/table';
import NumberFormat from 'react-number-format';

const navigate = (e, id) => {
  // TODO - pass policy number

  Liferay.Util.navigate('policy-details');
}

const download = (e, pathname) => {
  e.stopPropagation();

  // TODO - pass file path to download
}

const PolicyList = (props) => {
  const spritemap = Liferay.ThemeDisplay.getPathThemeImages() + '/cmic/icons.svg';

  if (props.isLoading) {
    return (<ClayLoadingIndicator />);
  }
  else {
    return (
      <ClayTable className="table-heading-nowrap">
        <ClayTable.Head>
          <ClayTable.Row>
            <ClayTable.Cell expanded headingCell>
              {Liferay.Language.get('type')}
              <a href="javascript:;" className="text-muted">
                <ClayIcon symbol={"sort"} spritemap={spritemap} />
              </a>
            </ClayTable.Cell>

            <ClayTable.Cell headingCell>
              {Liferay.Language.get('effective-date')}
              <a href="javascript:;" className="text-muted">
                <ClayIcon symbol={"sort"} spritemap={spritemap} />
              </a>
            </ClayTable.Cell>

            <ClayTable.Cell headingCell align="right">
              {Liferay.Language.get('transactions')}
              <a href="javascript:;" className="text-muted">
                <ClayIcon symbol={"sort"} spritemap={spritemap} />
              </a>
            </ClayTable.Cell>

            <ClayTable.Cell headingCell align="right">
              {Liferay.Language.get('amount-billed')}
              <a href="javascript:;" className="text-muted">
                <ClayIcon symbol={"sort"} spritemap={spritemap} />
              </a>
            </ClayTable.Cell>

            <ClayTable.Cell headingCell />

          </ClayTable.Row>
        </ClayTable.Head>
        {props.policyList != null &&
          <ClayTable.Body>
            {props.policyList.map((policy, index) => (
              <ClayTable.Row
                key={index}
                className="cursor-pointer"
                onClick={(e) => navigate(e, policy.policyNumber)}>
                <ClayTable.Cell>
                  <h5 className="font-weight-bold mb-0">{policy.policyType}</h5>
                  <small className="text-muted">#{policy.policyNumber}</small>
                </ClayTable.Cell>

                <ClayTable.Cell>{policy.effectiveDate} - {policy.expirationDate}</ClayTable.Cell>
                <ClayTable.Cell align="right">{policy.numTransactions}</ClayTable.Cell>

                <ClayTable.Cell align="right" className="h3 font-weight-bold">
                  <NumberFormat value={policy.totalBilledPremium} displayType={'text'} thousandSeparator={true} prefix={'$'} />
                </ClayTable.Cell>

                <ClayTable.Cell align="right">
                  <ClayButton
                    displayType="unstyled"
                    monospaced
                    className="text-primary"
                    onClick={(e) => download(e)}>
                    <ClayIcon symbol={"download"} spritemap={spritemap} className="lexicon-icon-lg" />
                  </ClayButton>
                </ClayTable.Cell>
              </ClayTable.Row>
            ))}
          </ClayTable.Body>
        }
      </ClayTable>
    );
  }
};

export default PolicyList;