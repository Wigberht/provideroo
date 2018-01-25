<script type="text/x-template" id="subscriber-profile-balance-template">
    <div>
        <div class="row">
            <h4>{{text_balance}}: {{balance}} {{currency}}</h4>
        </div>
        <div class="row">
            <div class="input-field col s8">
                <input placeholder="50"
                       id="replenish-amount"
                       type="text"
                       class="validate"
                       v-model="replenishAmount">
                <label for="replenish-amount">
                    {{text_replenish_by}}
                </label>
            </div>
        </div>
        <div class="row">
            <a @click.prevent="replenishBalance" class="btn">
                {{text_replenish }}
            </a>
        </div>
    </div>

</script>

<script>
    Vue.component('subscriber-profile-balance', {
        props: [
            'subscriber',
            'text_replenish',
            'text_balance',
            'text_replenish_by',
            'text_replenish_success',
            'text_replenish_fail',
        ],
        template: "#subscriber-profile-balance-template",
        data() {
            return {
                d_subscriber: JSON.parse(this.subscriber),
                balance: JSON.parse(this.subscriber).account.balance,
                currency: JSON.parse(this.subscriber).account.currencyShortname,
                replenishAmount: 50
            }
        },
        methods: {
            replenishBalance() {
                console.log(this.balance);
                console.log(this.replenishAmount);
                axios.post("/rest/user/balance/replenish", {
                    'userId': this.d_subscriber.user.id,
                    'amount': this.replenishAmount,
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast(this.text_replenish_success, 1500, "green darken-2")
                        this.addAmountToBalance();
                    } else {
                        Materialize.toast(this.text_replenish_fail, 1500, "red darken-2");
                    }
                }).catch((error) => {
                    Materialize.toast(this.text_replenish_fail, 1500, "red darken-2");
                    console.log(error);
                })
            },

            addAmountToBalance() {
                if (!isNaN(this.replenishAmount)) {
                    let amount = Number(this.replenishAmount);
                    amount = amount >= 0 ? amount : 0;
                    this.balance += amount;
                }
            }
        },
        mounted: function () {
            console.log(this.d_subscriber)
        },
        computed: {}
    })
</script>
