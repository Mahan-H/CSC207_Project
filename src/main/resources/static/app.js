/**
 * Parses a query parameter from the URL.
 * @param {string} name - The name of the parameter to retrieve.
 * @returns {string|null} The value of the parameter or null if not found.
 */
function getQueryParam(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}



const email = getQueryParam('email');

if (!email) {
    alert('Email is missing from URL parameters.');
    // Handle the missing email appropriately
}

document.getElementById('link-button').addEventListener('click', function() {
    const statusDiv = document.getElementById('status');
    statusDiv.textContent = 'Fetching Link Token...';
    console.log('Initiating Link Token request.');

    fetch('/api/plaid/create-link-token', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            clientName: "Osiris",
            countryCodes: ["US", "CA"],
            language: "en",
            userClientId: email,
            products: ["transactions"]
        })
    })
        .then(response => {
            console.log('Received response for Link Token:', response);
            if (!response.ok) {
                throw new Error('Failed to fetch Link Token: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Link Token Data:', data);
            const linkToken = data.linkToken;
            statusDiv.textContent = 'Initializing Plaid Link...';
            initializePlaidLink(linkToken);
        })
        .catch(error => {
            console.error('Error fetching Link Token:', error);
            statusDiv.textContent = 'Error fetching Link Token.';
        });
});


/**
 * Initializes Plaid Link with the provided Link Token.
 * @param {string} linkToken - The Link Token obtained from the backend.
 */
function initializePlaidLink(linkToken) {
    const statusDiv = document.getElementById('status');

    const handler = Plaid.create({
        token: linkToken,
        onSuccess: function(public_token, metadata) {
            console.log('Plaid Link Success. Public Token:', public_token);
            console.log('Metadata:', metadata);
            statusDiv.textContent = 'Exchanging Public Token...';
            exchangePublicToken(public_token);
        },
        onExit: function(err, metadata) {
            if (err != null) {
                console.error('Plaid Link error:', err);
                statusDiv.textContent = 'Error during Plaid Link.';
            } else {
                console.log('Plaid Link exited without linking.');
                statusDiv.textContent = 'Plaid Link exited without linking.';
            }
        }
    });

    handler.open();
}

/**
 * Sends the Public Token to the backend to exchange for an Access Token.
 * @param {string} publicToken - The Public Token received from Plaid Link.
 */
function exchangePublicToken(publicToken) {
    const statusDiv = document.getElementById('status');

    fetch('/api/plaid/exchange-public-token', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            publicToken: publicToken,
            email: email
        })
    })
        .then(response => {
            console.log('Received response for Exchange Token:', response);
            if (!response.ok) {
                throw new Error('Failed to exchange Public Token: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Exchange Token Data:', data);
            const accessToken = data.access_token;
            const itemId = data.item_id;
            console.log('Access Token:', accessToken);
            console.log('Item ID:', itemId);
            statusDiv.textContent = 'Bank account linked successfully!';
        })
        .catch(error => {
            console.error('Error exchanging Public Token:', error);
            statusDiv.textContent = 'Error exchanging Public Token.';
        });
}
