<script type="text/x-template" id="tariff-row-template">
    <div v-if="show">
        <template v-if="isAdmin">
            <tariff-row-admin
                @update="updateTariff"
                @delete="deleteTariff"

                :tariff="tariff"
            ></tariff-row-admin>
        </template>

        <template v-if="!isAdmin">
            <tariff-row-subscriber
                :tariff="tariff"
                :user="user"
                :subscriptions="subscriptions"
            ></tariff-row-subscriber>
        </template>
    </div>
</script>

<script>
    Vue.component('tariff-row', {
        props: ['tariff', 'user', 'subscriptions'],
        data() {
            return {
                isAdmin: JSON.parse(this.user).admin,
                show: true
            }
        },
        methods: {
            updateTariff(tariff) {
                axios.post("/api/tariff/update", {
                    'id': tariff.id,
                    'title': tariff.title,
                    'description': tariff.description,
                    'numberOfDays': tariff.numberOfDays,
                    'cost': tariff.cost,
                    'currencyShortname': tariff.currencyShortname,
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast("SUCCESS", 1500)
                    } else {
                        Materialize.toast("FAIL", 1500);
                    }
                }).catch((error) => {
                    Materialize.toast("FAIL", 1500);
                    console.log(error);
                });
            },
            deleteTariff(tariffId) {
                axios.post("/api/tariff/delete", {
                    'tariffId': tariffId
                }).then(response => {
                    console.log(response);
                    if (response.data.success) {
                        $('.tooltipped').tooltip('remove');
                        this.show = false;
                        Materialize.toast("DELETE SUCCESS", 1500, "green darken-2");
                    }
                }).catch(error => {
                    Materialize.toast("UNABLE TO DELETE, there may be subscribers", 1500, "red darken-2");
                })
            }
        },
        mounted() { },
        template: "#tariff-row-template",
    })
</script>